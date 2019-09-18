package Clases;

import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class Correo extends Thread{
    
    private Properties propiedades = new Properties();
    private String correo;
    private String clave;
    private Session session;
    private String correoCoor;
    private String asunto;
    private List<Etapas> etapas;
    private String mensaje;
    private JLabel texto;
    
    public Correo(String correo, String clave){
        super();
        //Información de conexión al servidor de GMAIL.
        propiedades.put("mail.transport.protocol", "smtp");
        propiedades.put("mail.smtp.host", "smtp.gmail.com");
        propiedades.put("mail.smtp.starttls.enable", "true");
        propiedades.put("mail.smtp.auth", "true");
        propiedades.put("mail.smtp.port", "587");
        
        //Información del correo emisor 
        this.correo = correo;
        this.clave = clave;
    }
    
    public boolean Conectar(String correoCoor, String asunto, List<Etapas> etapas, String mensaje, JLabel texto) throws NoSuchProviderException{
        this.correoCoor = correoCoor;
        this.asunto = asunto;
        this.etapas = etapas;
        this.mensaje = mensaje;
        this.texto = texto;
        session = Session.getInstance(propiedades,
                new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(correo,clave);
                    }
                });
        session.setDebug(false);
        
        Transport t = session.getTransport("smtp");
        try {
            t.connect(this.correo, clave);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "¡Hay un error con los datos ingresados!");
            return false;
        }
        
    }

    @Override
    public void run() {
        //super.run(); //To change body of generated methods, choose Tools | Templates.
        MimeMultipart datosCorreo = new MimeMultipart();
        try {
            for(int i = 0; i < etapas.size(); i++){
                if(etapas.get(i).dameNombre() != ""){ 
                    BodyPart archivoAdjunto = new MimeBodyPart();
                    archivoAdjunto.setDataHandler(new DataHandler(new FileDataSource(etapas.get(i).dameRuta())));
                    System.out.println(etapas.get(i).dameRuta());
                    archivoAdjunto.setFileName(etapas.get(i).dameNombre());
                    datosCorreo.addBodyPart(archivoAdjunto);
                }
            }
            BodyPart texto = new MimeBodyPart();
            texto.setText(mensaje);
            datosCorreo.addBodyPart(texto);
            MimeMessage escritoCorreo = new MimeMessage(session);
            escritoCorreo.setFrom(new InternetAddress(correo));
            escritoCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(correoCoor));
            escritoCorreo.setSubject(asunto);
            escritoCorreo.setContent(datosCorreo);
            Transport t = session.getTransport("smtp");
            t.connect(correo,clave);
            t.sendMessage(escritoCorreo, escritoCorreo.getAllRecipients());
            t.close();
            texto.setText("Correo enviado a " + correoCoor);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "¡Ha ocurrido un error con el envío a " + correoCoor + "!");
        }
    }
    
    
    
    
    public void enviarPlanillas(){
        
        
    }
    
}
