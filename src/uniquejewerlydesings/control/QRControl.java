/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniquejewerlydesings.control;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import uniquejewerlydesings.vista.GenerarQR;

/**
 *
 * @author LENOVO
 */
public class QRControl {

    private GenerarQR vistaQr;

    public QRControl(GenerarQR vistaQr) {
        this.vistaQr = vistaQr;
    }
    
    public void iniciarControl(){
     vistaQr.getBtnGenerar().addActionListener(e -> generarQr());
    }
    
    public void generarQr(){
         int size = 135;
        String FileType = "png";

        String codigo = vistaQr.getTxtContenido().getText().trim();
        // Elegir la ruta de la imagen
        String filePath = "";
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (chooser.showOpenDialog(this.vistaQr) == JFileChooser.APPROVE_OPTION) {
            filePath = chooser.getSelectedFile().getAbsolutePath();
        }

        // Generar el nombre
        UUID uuid = UUID.randomUUID();
        String randonName = uuid.toString();

        // Generar el QR 
        QRCodeWriter qrcode = new QRCodeWriter();
        try {
            BitMatrix matrix = qrcode.encode(codigo, BarcodeFormat.QR_CODE, size, size);
            File f = new File(filePath + "/" + randonName + "." + FileType);
            int matrixWidth = matrix.getWidth();
            BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
            image.createGraphics();

            Graphics2D gd = (Graphics2D) image.getGraphics();
            gd.setColor(Color.WHITE); // Fondo
            gd.fillRect(0, 0, size, size);
            gd.setColor(Color.black); // Qr

            for (int b = 0; b < matrixWidth; b++) {
                for (int j = 0; j < matrixWidth; j++) {
                    if (matrix.get(b, j)) {
                        gd.fillRect(b, j, 1, 1);
                    }
                }
            }
            
            // Mostrar la imagen generada
            ImageIO.write(image, FileType, f);
            Image mImagen = new ImageIcon(filePath + "/" + randonName + "." + FileType).getImage();
            ImageIcon mIcono = new ImageIcon(mImagen.getScaledInstance(vistaQr.getLbQr().getWidth(), vistaQr.getLbQr().getHeight(), 0));
            vistaQr.getLbQr().setIcon(mIcono);
            
        } catch (WriterException ex) {
            Logger.getLogger(GenerarQR.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GenerarQR.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
