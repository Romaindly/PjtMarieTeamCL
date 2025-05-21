package fr.dly_vrn;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.Collection;

/**
 * Classe utilitaire permettant de générer un document PDF
 * présentant une collection de bateaux voyageurs avec leurs
 * descriptions et images associées.
 */
public class Brochure_PDF {

    /**
     * Génère un PDF en parcourant la collection de bateaux.
     * Pour chaque bateau, son texte (via toString()) et son image (si disponible) sont ajoutés.
     *
     * @param nomFichier nom du fichier PDF généré
     * @param bateaux    collection de BateauVoyageur à inclure
     */
    public static void genererPDF(String nomFichier, Collection<BateauVoyageur> bateaux) {
        Document document = new Document(PageSize.A4);
        try {
            PdfWriter.getInstance(document, new FileOutputStream(nomFichier));
            document.open();

            Paragraph titre = new Paragraph("Brochure des Bateaux Voyageurs\n\n",
                    new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.BLACK));
            titre.setAlignment(Element.ALIGN_CENTER);
            document.add(titre);


            for (BateauVoyageur bv : bateaux) {
                // Description du bateau en paragraphes distincts
                document.add(new Paragraph("Nom du bateau : " + bv.getNom()+ "\n\n",
                new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLACK)));
                document.add(new Paragraph("Longueur : " + String.format("%.2f", bv.getLongueurBat()) + " mètres"));
                document.add(new Paragraph("Largeur : " + String.format("%.2f", bv.getLargeurBat()) + " mètres"));
                document.add(new Paragraph("Vitesse : " + String.format("%.2f", bv.getVitesseBatVoy()) + " noeuds \n"));

                // Ajouter l'image si disponible
                if (bv.getImageBatVoy() != null && !bv.getImageBatVoy().trim().isEmpty()) {
                    URL imageUrl = Brochure_PDF.class.getResource(bv.getImageBatVoy());
                    if (imageUrl != null) {
                        try {
                            Image img = Image.getInstance(imageUrl);
                            img.scaleToFit(400, 200);
                            img.setAlignment(Image.ALIGN_CENTER);
                            document.add(img);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.err.println("Image introuvable pour " + bv.getNom() + " : " + bv.getImageBatVoy());
                    }
                }

                // Ajouter un séparateur
                document.add(new Paragraph(" \n----------------------------------------------------"));
                document.add(Chunk.NEWLINE);
            }

            document.close();
            System.out.println("PDF généré : " + nomFichier);

        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode main pour tester la génération avec tous les bateaux voyageurs.
     * Le PDF est généré sous le nom "BateauVoyageur.pdf" dans le répertoire courant.
     *
     * @param args arguments de la ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        Collection<BateauVoyageur> bateaux = Passerelle.chargerLesBateauxVoyageurs();
        genererPDF("BateauVoyageur.pdf", bateaux);
    }
}