package com.example.rank.word;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * @ClassName wordTeset
 * @Description
 * @Author ts
 * @Date 2022/9/6 9:47
 * @Version 1.0
 */
public class WordTest {


    public static void main(String[] args) throws IOException {
        HashMap<String, String> params = new HashMap<>();
        params.put("granteeName", "test1");
        params.put("companyName", "test1");
        params.put("sealName", "test1");
        params.put("granteeCidTypeName", "test1");
        params.put("granteeCid", "test1");
        params.put("grantorName", "test1");
        params.put("grantTime", "test1");
        File file = PdfUtil.genTempPdf(
                "templates/sealManagerAuthFile-20220512.docx",
                "sealManagerAuthUnsignFile", params);
        System.out.println(file.getPath());
    }

}
