package fr.dly_vrn;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JeuEnregistrement {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private boolean fin;

    /**
     * Constructeur : exécute la requête SQL et positionne le curseur sur le premier enregistrement.
     * Si aucun enregistrement n'est trouvé, la variable fin est mise à true.
     *
     * @param sql requête SQL à exécuter
     */

    public JeuEnregistrement(String sql) {
        try {
            // Charger les propriétés de connexion depuis db.properties
            Properties props = loadDBProperties();
            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");

            // Établir la connexion
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(sql);

            // Positionner le curseur sur le premier enregistrement
            fin = !resultSet.next();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            fin = true;
        }
    }

    /**
     * Charge le fichier db.properties depuis le classpath.
     *
     * @return un objet Properties contenant les infos de connexion
     * @throws IOException si le fichier est introuvable ou illisible
     */

    private Properties loadDBProperties() throws IOException {
        Properties props = new Properties();
        InputStream input = getClass().getClassLoader().getResourceAsStream("db.properties");
        if (input == null) {
            throw new IOException("Fichier db.properties introuvable");
        }
        props.load(input);
        return props;
    }

    /**
     * Méthode fin().
     * Retourne true si le curseur a atteint la fin des enregistrements.
     *
     * @return true si plus de données, false sinon
     */

    public boolean fin() {
        return fin;
    }

    /**
     * Avance le curseur vers l'enregistrement suivant.
     */

    public void suivant() {
        try {
            if (resultSet.next()) {
                fin = false;
            } else {
                fin = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            fin = true;
        }
    }

    /**
     * Renvoie la valeur du champ 'nomChamp' de l'enregistrement courant.
     *
     * @param nomChamp nom du champ
     * @return valeur du champ ou null si erreur
     */

    public Object getValeur(String nomChamp) {
        try {
            if (!fin) {
                return resultSet.getObject(nomChamp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Ferme le ResultSet, le Statement et la Connection.
     */

    public void fermer() {
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
