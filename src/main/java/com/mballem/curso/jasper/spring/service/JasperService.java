package com.mballem.curso.jasper.spring.service;

import com.mballem.curso.jasper.spring.JasperReportsSpringApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import net.sf.jasperreports.engine.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

@Service
public class JasperService {

    private static final String JASPER_DIRETORIO = "classpath:jasper/";
    private static final String JASPER_PREFIXO = "funcionarios-01";
    private static final String JASPER_SUFIXO = ".jasper";

    @Autowired
    private Connection connection;

    private Map<String, Object> params = new HashMap<>();

    public void addParams(String key, Object value){
        this.params.put(key,value);
    }

    public byte[] exportarPDF(String code){
       byte[] bytes = null;
        try {
            String diretorio = JASPER_DIRETORIO.concat(JASPER_PREFIXO.concat(JASPER_SUFIXO));
            File file = ResourceUtils.getFile(diretorio);
            JasperPrint print = JasperFillManager.fillReport(file.getAbsolutePath(), params,connection);
            bytes = JasperExportManager.exportReportToPdf(print);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }

        return  bytes;

    }
}
