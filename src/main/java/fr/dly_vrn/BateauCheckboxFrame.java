package fr.dly_vrn;

import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 * Fenêtre principale de sélection des bateaux à inclure dans une brochure PDF.
 * Les bateaux sont présentés avec une image miniature, une liste sélectionnable,
 * et une prévisualisation avec options d'édition.
 */

public class BateauCheckboxFrame extends JFrame {

    // Panel principal pour la liste des cases à cocher
    private JPanel checkboxPanel;
    private JScrollPane checkboxScrollPane;

    // JList pour afficher les bateaux sélectionnés (mise à jour automatique)
    private DefaultListModel<String> selectedBoatsListModel;
    private JList<String> selectedBoatsList;
    private JScrollPane selectedBoatsScrollPane;

    // Bouton pour générer le PDF
    private JButton generateButton;

    // Liste de JCheckBox pour tous les bateaux
    private List<JCheckBox> checkBoxList;

    // Panel de prévisualisation (affichage de l'image agrandie et infos)
    private JPanel previewPanel;
    private JLabel imageLabel;
    private JTextArea infoTextArea;
    private JButton editButton;
    private JDialog editDialog;
    private BateauVoyageur currentBoat;

    /**
     * Constructeur par défaut initialisant la fenêtre principale et ses composants.
     */

    public BateauCheckboxFrame() {
        setTitle("Sélectionnez les bateaux pour la brochure");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        checkBoxList = new ArrayList<>();
        initComponents();
        loadBateaux();
    }

    private void initComponents() {
        // Zone de sélection des bateaux sélectionnés sous forme de JList
        selectedBoatsListModel = new DefaultListModel<>();
        selectedBoatsList = new JList<>(selectedBoatsListModel);
        selectedBoatsList.setVisibleRowCount(3);
        selectedBoatsScrollPane = new JScrollPane(selectedBoatsList);
        selectedBoatsScrollPane.setBorder(BorderFactory.createTitledBorder("Bateaux sélectionnés"));

        // Panel pour les cases à cocher avec un layout vertical
        checkboxPanel = new JPanel();
        checkboxPanel.setLayout(new BoxLayout(checkboxPanel, BoxLayout.Y_AXIS));
        checkboxScrollPane = new JScrollPane(checkboxPanel);

        // Panel de prévisualisation
        previewPanel = new JPanel(new BorderLayout());
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoTextArea = new JTextArea();
        infoTextArea.setEditable(false);
        infoTextArea.setLineWrap(true);
        infoTextArea.setWrapStyleWord(true);
        JScrollPane infoScrollPane = new JScrollPane(infoTextArea);

        // Add edit button
        editButton = new JButton("Modifier");
        editButton.addActionListener(e -> showEditDialog());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(editButton);

        previewPanel.add(imageLabel, BorderLayout.CENTER);
        previewPanel.add(infoScrollPane, BorderLayout.SOUTH);
        previewPanel.add(buttonPanel, BorderLayout.NORTH);

        // JSplitPane pour diviser la zone avec les cases à cocher et le panneau de prévisualisation
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, checkboxScrollPane, previewPanel);
        splitPane.setDividerLocation(300);

        // Bouton de génération du PDF
        generateButton = new JButton("Générer la brochure PDF");
        generateButton.addActionListener(e -> generatePDF());

        // Organisation principale du frame
        setLayout(new BorderLayout());
        add(selectedBoatsScrollPane, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
        add(generateButton, BorderLayout.SOUTH);
    }

    private void loadBateaux() {
        // Charger tous les bateaux via Passerelle
        Collection<BateauVoyageur> bateaux = Passerelle.chargerLesBateauxVoyageurs();
        if (bateaux.isEmpty()) {
            checkboxPanel.add(new JLabel("Aucun bateau trouvé."));
        } else {
            for (BateauVoyageur bv : bateaux) {
                // Charger l'icône redimensionnée à 50x50 depuis le chemin (ex: "/images/kor_ant.jpg")
                ImageIcon icon = getBoatIcon(bv.getImageBatVoy(), 50, 50);
                JCheckBox checkBox = new JCheckBox(bv.getNom(), icon);
                checkBox.setHorizontalAlignment(SwingConstants.LEFT);
                checkBox.setHorizontalTextPosition(SwingConstants.RIGHT);
                checkBox.setIconTextGap(10);
                // Stocker l'objet bateau dans la case
                checkBox.putClientProperty("bateau", bv);

                // Ajout d'un ItemListener pour mettre à jour la JList des bateaux sélectionnés
                checkBox.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        updateSelectedDisplay();
                    }
                });

                // Ajouter un MouseListener pour afficher la prévisualisation lors du clic
                checkBox.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        showPreview(bv);
                    }
                });

                checkBoxList.add(checkBox);
                checkboxPanel.add(checkBox);
            }
        }
        checkboxPanel.revalidate();
        checkboxPanel.repaint();
        updateSelectedDisplay();
    }

    /**
     * Met à jour la JList en affichant les noms des bateaux sélectionnés.
     */

    private void updateSelectedDisplay() {
        selectedBoatsListModel.clear();
        for (JCheckBox cb : checkBoxList) {
            if (cb.isSelected()) {
                selectedBoatsListModel.addElement(cb.getText());
            }
        }
    }

    /**
     * Retourne une ImageIcon redimensionnée avec une qualité améliorée.
     *
     * @param imagePath chemin relatif dans le classpath (ex. "/images/kor_ant.jpg")
     * @param width largeur désirée en pixels
     * @param height hauteur désirée en pixels
     * @return l'ImageIcon ou null si l'image n'est pas trouvée
     */

    private ImageIcon getBoatIcon(String imagePath, int width, int height) {
        try {
            java.net.URL imgURL = getClass().getResource(imagePath);
            if (imgURL != null) {
                BufferedImage original = ImageIO.read(imgURL);
                if (original != null) {
                    BufferedImage scaled = getScaledImage(original, width, height);
                    return new ImageIcon(scaled);
                } else {
                    System.err.println("Impossible de lire l'image : " + imagePath);
                    return null;
                }
            } else {
                System.err.println("Image non trouvée : " + imagePath);
                return null;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Redimensionne l'image avec Graphics2D pour une meilleure qualité.
     *
     * @param src l'image source
     * @param width la largeur souhaitée
     * @param height la hauteur souhaitée
     * @return la BufferedImage redimensionnée
     */

    private static BufferedImage getScaledImage(BufferedImage src, int width, int height) {
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resized.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.drawImage(src, 0, 0, width, height, null);
        g2.dispose();
        return resized;
    }

    /**
     * Affiche dans le panneau de prévisualisation l'image agrandie (200x200) et les informations du bateau.
     *
     * @param bv l'objet BateauVoyageur à prévisualiser
     */

    private void showPreview(BateauVoyageur bv) {
        currentBoat = bv;
        ImageIcon icon = getBoatIcon(bv.getImageBatVoy(), 200, 200);
        imageLabel.setIcon(icon);
        infoTextArea.setText(bv.toString());
        previewPanel.revalidate();
        previewPanel.repaint();
    }

    /**
     * Récupère les bateaux sélectionnés et génère le PDF via la classe Brochure_PDF.
     */

    private void generatePDF() {
        List<BateauVoyageur> selectedBateaux = new ArrayList<>();
        for (JCheckBox cb : checkBoxList) {
            if (cb.isSelected()) {
                BateauVoyageur bv = (BateauVoyageur) cb.getClientProperty("bateau");
                selectedBateaux.add(bv);
            }
        }
        if (selectedBateaux.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner au moins un bateau.", "Attention", JOptionPane.WARNING_MESSAGE);
        } else {
            Brochure_PDF.genererPDF("BateauVoyageur_Selection.pdf", selectedBateaux);
            JOptionPane.showMessageDialog(this, "Le PDF a été généré avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void showEditDialog() {
        if (currentBoat == null) return;

        editDialog = new JDialog(this, "Modifier le bateau", true);
        editDialog.setLayout(new GridLayout(6, 2));

        JTextField nomField = new JTextField(currentBoat.getNom());
        JTextField longueurField = new JTextField(String.valueOf(currentBoat.getLongueurBat()));
        JTextField largeurField = new JTextField(String.valueOf(currentBoat.getLargeurBat()));
        JTextField vitesseField = new JTextField(String.valueOf(currentBoat.getVitesseBatVoy()));
        JTextField imageField = new JTextField(currentBoat.getImageBatVoy());

        editDialog.add(new JLabel("Nom:"));
        editDialog.add(nomField);
        editDialog.add(new JLabel("Longueur:"));
        editDialog.add(longueurField);
        editDialog.add(new JLabel("Largeur:"));
        editDialog.add(largeurField);
        editDialog.add(new JLabel("Vitesse:"));
        editDialog.add(vitesseField);
        editDialog.add(new JLabel("Image:"));
        editDialog.add(imageField);

        JButton saveButton = new JButton("Sauvegarder");
        saveButton.addActionListener(e -> {
            try {
                currentBoat.setNom(nomField.getText());
                currentBoat.setLongueurBat(Double.parseDouble(longueurField.getText()));
                currentBoat.setLargeurBat(Double.parseDouble(largeurField.getText()));
                currentBoat.setVitesseBatVoy(Double.parseDouble(vitesseField.getText()));
                currentBoat.setImageBatVoy(imageField.getText());
                // Update preview
                showPreview(currentBoat);
                editDialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(editDialog, "Veuillez entrer des valeurs numériques valides", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton cancelButton = new JButton("Annuler");
        cancelButton.addActionListener(e -> editDialog.dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        editDialog.add(buttonPanel);

        editDialog.pack();
        editDialog.setLocationRelativeTo(this);
        editDialog.setVisible(true);
    }

    /**
     * Méthode principale pour lancer l'interface graphique.
     *
     * @param args arguments inutilisés
     */

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BateauCheckboxFrame().setVisible(true));
    }
}
