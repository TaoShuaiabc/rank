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

 /*   public static void main(String[] args) throws IOException {
        Map<String, Object> datas = new HashMap<String, Object>();
        List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
        for (int i = 0; i < 6; i++) {
            Map<String,Object> detailMap = new HashMap<String, Object>();
            detailMap.put("index", i+1);
            detailMap.put("username", "姓名"+i);
            detailMap.put("age", i*10);
            detailMap.put("sex", i*20);
            list.add(detailMap);
        }
        HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
        Configure config = Configure.newBuilder().bind("list", policy).build();
        datas.put("list", list);
        XWPFTemplate template = XWPFTemplate.compile("E:\\code\\rank\\src\\main\\resources\\templates\\sealManagerAuthFile-20220512.docx",config).render(datas);
        FileOutputStream out = new FileOutputStream("C:/Users/ASUS/Desktop/开发文件/1.docx");
        template.write(out);
    }*/

    public static void main(String[] args) throws IOException {
        HashMap<String, String> params = new HashMap<>();
        params.put("granteeName", "test1");
        params.put("companyName", "test1");
        params.put("sealName", "test1");
        params.put("granteeCidTypeName", "test1");
        params.put("granteeCid", "test1");
        params.put("grantorName", "test1");
        params.put("grantTime", "test1");
        params.put("username", "陶帅");
        params.put("age", "18");
        params.put("sex", "男");
        File file = PdfUtil.genTempPdf(
                "templates/sealManagerAuthFile-20220512.docx",
                "sealManagerAuthUnsignFile", params);
        System.out.println(file.getPath());
    }
}
