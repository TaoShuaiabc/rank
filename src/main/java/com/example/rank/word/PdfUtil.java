/*
package com.example.rank.word;

import com.deepoove.poi.XWPFTemplate;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.*;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.UUID;

*/
/**
 * @author linsonxie
 * @since 2020-07-23
 *//*

public class PdfUtil {

    */
/**
     * 填充word模板并生成PDF
     *//*

    public static String genPdfFromWordModel(String wordModelPath, String targetPathPre, Map<String, String> params) throws IOException {
        String uuid = UUID.randomUUID().toString();
        File dirFile = new File(targetPathPre);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        String wordPath = targetPathPre + uuid + ".docx";
        //word生成
        genWord(wordModelPath, wordPath, params);
        //word转pdf
        String pdfName = uuid + ".pdf";
        String outPath = targetPathPre + pdfName;
        InputStream source = new FileInputStream(wordPath);
        OutputStream target = new FileOutputStream(outPath);
        PdfOptions options = PdfOptions.create();
        wordConverterToPdf(source, target, options);
        //删除生成的word中间文件
        File file = new File(wordPath);
        file.delete();
        return pdfName;
    }

    */
/**
     * poi-tl替换word模板
     *
     * @param wordModelPath
     * @param targetPath
     * @param params
     * @throws IOException
     *//*

    public static void genWord(String wordModelPath, String targetPath, Map<String, String> params) throws IOException {
        XWPFTemplate template = XWPFTemplate.compile(wordModelPath).render(params);
        template.writeToFile(targetPath);
    }

    */
/**
     * 生成PDF临时文件
     * wordModelPath 模板路径
     * pdfNamePrefix 临时文件前缀
     * params 填充模板的参数
     *//*

    public static File genTempPdf(String wordModelPath, String pdfNamePrefix, Map<String, String> params) throws IOException {
        Resource resource = new ClassPathResource(wordModelPath);
        try(InputStream inputStream = resource.getInputStream()){
            // word模板生成word
            File word = File.createTempFile("PdfUtil.genTempPdf", ".docx");
            XWPFTemplate template = XWPFTemplate.compile(inputStream).render(params);
            String wordPath = word.getAbsolutePath();
            template.writeToFile(wordPath);
            // word转为pdf
            try(FileInputStream wordInputStream = new FileInputStream(word)){
                XWPFDocument doc = new XWPFDocument(wordInputStream);
                File pdf = File.createTempFile(pdfNamePrefix, ".pdf");
                try(FileOutputStream pdfOutputStream = new FileOutputStream(pdf)) {
                    PdfConverter.getInstance().convert(doc, pdfOutputStream, PdfOptions.create());
                }
                return pdf;
            }
        }
    }

    */
/**
    * 通过模板生成pdf字节流
    *//*

    public static ByteArrayOutputStream genPdfOutputStream(String wordModelPath, Map<String, String> params) throws IOException {
        Resource resource = new ClassPathResource(wordModelPath);
        try(InputStream inputStream = resource.getInputStream()){
            // word模板生成word字节流
            ByteArrayOutputStream wordOutputStream = new ByteArrayOutputStream();
            XWPFTemplate template = XWPFTemplate.compile(inputStream).render(params);
            template.write(wordOutputStream);
            // word字节流转为pdf字节流
            XWPFDocument doc = new XWPFDocument(new ByteArrayInputStream(wordOutputStream.toByteArray()));
            ByteArrayOutputStream target = new ByteArrayOutputStream();
            PdfConverter.getInstance().convert(doc, target, PdfOptions.create());
            return target;
        }
    }

    */
/**
     * 将word文档， 转换成pdf, 中间替换掉变量
     *
     * @param source 源为word文档， 必须为docx文档
     * @param target 目标输出
     * @throws Exception
     *//*

    private static void wordConverterToPdf(InputStream source,
                                           OutputStream target) throws Exception {
        wordConverterToPdf(source, target, null);
    }

    */
/**
     * 将word文档， 转换成pdf, 中间替换掉变量
     *
     * @param source  源为word文档， 必须为docx文档
     * @param target  目标输出
     * @param options PdfOptions.create().fontEncoding( "windows-1250" ) 或者其他
     * @throws Exception
     *//*

    private static void wordConverterToPdf(InputStream source, OutputStream target,
                                           PdfOptions options) throws IOException {
        XWPFDocument doc = new XWPFDocument(source);
        PdfConverter.getInstance().convert(doc, target, options);
    }

    */
/**
     * 替换段落中内容
     *//*

    private static void paragraphReplace(List<XWPFParagraph> paragraphs, Map<String, String> params) {
        if (MapUtils.isNotEmpty(params)) {
            for (XWPFParagraph p : paragraphs) {
                for (XWPFRun r : p.getRuns()) {
                    String content = r.getText(r.getTextPosition());
                    if (StringUtils.isNotEmpty(content) && params.containsKey(content)) {
                        r.setText(params.get(content), 0);
                    }
                }
            }
        }
    }


    */
/**
     * 给PDF添加签名域
     *
     * @param sourcePath 目前PDF路径
     * @param signName   签名域名称，例：sign_area_0
     * @param pageNo     文件的页码：从 1 开始，而不是 0
     * @param x          左下角到右下角为  x 轴，左下角到左上角为 y 轴
     * @param y
     * @param width      签名域：宽
     * @param height     签名域：高
     * @throws IOException
     * @throws DocumentException
     *//*

    public static String genSignArea(String sourcePath, String targetPathPre, String signName, int pageNo, int x, int y, int width, int height) throws IOException, DocumentException {
        String uuid = UUID.randomUUID().toString();
        File dirFile = new File(targetPathPre);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        String fileName = uuid + ".pdf";
        String signPath = targetPathPre + fileName;
        PdfReader reader = new PdfReader(sourcePath);
        PdfStamper pdfStamper = new PdfStamper(reader, new FileOutputStream(new File(signPath)));
        PdfWriter writer = pdfStamper.getWriter();
        // 创建签名域区域的矩形 坐标数组
        // 坐标系远点位于页面左下角，左下角到右下角为  x 轴，左下角到左上角为 y 轴
        Rectangle areaSignatureRect = new Rectangle(// 签名域区域，由两个对角点构成的矩形区域
                x, // 点1 x坐标
                y, // 点1 y坐标
                x + width, // 点2 x坐标
                y + height // 点2 y坐标
        );
        PdfFormField pdfFormField = PdfFormField.createSignature(writer);
        // 签名域标识 -- 签名域域名 如果已经存在同名的会报错
        pdfFormField.setFieldName(signName);
        // PDF 文件的页码从 1 开始，而不是 0
        pdfFormField.setPage(pageNo);
        pdfFormField.setWidget(areaSignatureRect, PdfAnnotation.HIGHLIGHT_OUTLINE); // 高亮显示
        pdfStamper.addAnnotation(pdfFormField, 1);
        pdfStamper.close();
        return fileName;
    }
}
*/
