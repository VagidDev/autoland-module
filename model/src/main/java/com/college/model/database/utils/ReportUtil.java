package com.college.model.database.utils;

import com.college.model.database.SessionManager;
//import net.sf.jasperreports.engine.JRDataSource;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.hibernate.Session;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportUtil {
    public static void generateReport() {
        System.out.println("Generate Report");
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            String query = "SELECT c_id, u_name, u_surname, d_name, a_mark, a_model, e_name, w_name, c_data\n" +
                    "FROM (((((au_users INNER JOIN au_contracts ON au_users.u_id=au_contracts.c_user_id)\n" +
                    "INNER JOIN au_dealers ON au_dealers.d_id=au_contracts.c_dealer_id)\n" +
                    "INNER JOIN au_warranties  ON au_warranties.w_id=au_contracts.c_warranty_id)\n" +
                    "INNER JOIN au_automobiles ON au_automobiles.a_id=au_contracts.c_auto_id)\n" +
                    "INNER JOIN au_equipments ON au_equipments.e_auto_id=au_contracts.c_auto_id \n" +
                    "AND au_equipments.e_id=au_contracts.c_equip_id);";

            List<Object[]> contracts = session.createNativeQuery(query).list();

            // Передача данных в отчет
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("ReportTitle", "Contracts Report");

            // Загрузка шаблона
            InputStream reportStream = ReportUtil.class.getResourceAsStream("/reports/contract_report.jasper");
            System.out.println(reportStream != null);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportStream);


            // Создание источника данных для отчета
            JRDataSource dataSource = new JRBeanCollectionDataSource(contracts);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            // Показ отчета
            JasperViewer.viewReport(jasperPrint, false);

            try {
                Class.forName("net.sf.jasperreports.engine.JRDataSource");
                System.out.println("JasperReports найден!");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("Ошибка: JasperReports не найден!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
