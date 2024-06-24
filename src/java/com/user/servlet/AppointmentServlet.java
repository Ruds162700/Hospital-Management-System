/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.user.servlet;

/**
 *
 * @author rudra
 */
import com.dao.AppointmentDao;
import com.dao.DoctorDao;
import com.db.DBconnect;
import com.entity.Appointment;
import com.entity.Doctor;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Date;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;

public class AppointmentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("userid"));
        String fullname = req.getParameter("fullname");
        String gender = req.getParameter("gender");
        String age = req.getParameter("age");
        String appoint_date = req.getParameter("appoint_date");
        String email = req.getParameter("email");
        String phno = req.getParameter("phno");
        String diseases = req.getParameter("diseases");
        int doctor_id = Integer.parseInt(req.getParameter("doct"));
        String address = req.getParameter("address");
        DoctorDao dao2 = new DoctorDao(DBconnect.getconn());
        Doctor d = dao2.getDoctorById(doctor_id);
        String docname = d.getFullName();
        // Create Appointment object
        Appointment ap = new Appointment(userId, fullname, gender, age, appoint_date, email, phno, diseases, doctor_id, address, "pending");

        // Add appointment to database
        AppointmentDao dao = new AppointmentDao(DBconnect.getconn());
        HttpSession session = req.getSession();
        if (dao.addAppointment(ap)) {
            session.setAttribute("succMsg", "Appointment Added Successfully");

            // Generate PDF with styling and QR code
            resp.setContentType("application/pdf");
            resp.setHeader("Content-Disposition", "attachment; filename=\"appointment.pdf\"");
            Document document = new Document();
            try {
                PdfWriter.getInstance(document, resp.getOutputStream());
                document.open();

                // Add hospital symbol
                Image hospitalImage = Image.getInstance("C:\\Users\\rudra\\Downloads\\Hospital.png");
                hospitalImage.setAlignment(Element.ALIGN_CENTER);
                document.add(hospitalImage);

                // Add header "Medi Care" in green color
                Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 36, BaseColor.GREEN);
                Paragraph header = new Paragraph("Medi Care", headerFont);
                header.setAlignment(Element.ALIGN_CENTER);
                document.add(header);

                // Appointment Details
                Font detailsFont = FontFactory.getFont(FontFactory.COURIER, 12, BaseColor.BLACK);
                document.add(Chunk.NEWLINE);
                document.add(new Paragraph("Date: " + new Date(), detailsFont));
                document.add(new Paragraph("Full Name: " + fullname, detailsFont));
                document.add(new Paragraph("Gender: " + gender, detailsFont));
                document.add(new Paragraph("Age: " + age, detailsFont));
                document.add(new Paragraph("Appointment Date: " + appoint_date, detailsFont));
                document.add(new Paragraph("Diseases: " + diseases, detailsFont));
                document.add(new Paragraph("Doctor: " + docname, detailsFont));
                document.add(new Paragraph("Email: " + email, detailsFont));
                document.add(new Paragraph("Phone Number: " + phno, detailsFont));
                document.add(new Paragraph("Address: " + address, detailsFont));

                // Concatenate appointment details into a single string
                String appointmentDetails = "Date: " + new Date() + "\n"
                        + "Full Name: " + fullname + "\n"
                        + "Gender: " + gender + "\n"
                        + "Age: " + age + "\n"
                        + "Appointment Date: " + appoint_date + "\n"
                        + "Email: " + email + "\n"
                        + "Phone Number: " + phno + "\n"
                        + "Address: " + address + "\n"
                        + "Diseases: " + diseases + "\n"
                        +"Doctor Name:"+docname + "\n";

                // Generate QR code using ZXing
                int qrCodeSize = 200; // Adjust size as needed
                BitMatrix bitMatrix = new QRCodeWriter().encode(appointmentDetails, BarcodeFormat.QR_CODE, qrCodeSize, qrCodeSize);
                Image qrImage = Image.getInstance(getBitMatrixAsByteArray(bitMatrix));
                qrImage.setAlignment(Element.ALIGN_CENTER);
                qrImage.scalePercent(50); // Adjust size if needed
                document.add(Chunk.NEWLINE);
                document.add(qrImage);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                document.close();
            }
        } else {
            session.setAttribute("errorMsg", "Something Went Wrong");
        }
        resp.sendRedirect("user_appointment.jsp");
    }

    // Convert BitMatrix to ByteArray
    private byte[] getBitMatrixAsByteArray(BitMatrix bitMatrix) {
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", byteArrayOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }
}
