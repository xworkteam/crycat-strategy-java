package com.xteam.crycat.utils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FileUtils {

    /**
     * 读取Text文件操作
     * @param
     * @return
     */
    public static String readText(String filePath) {
        StringBuilder lines = new StringBuilder();
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                lines.append(line).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines.toString();
    }

    /**
     * 写入Text文件操作
     * @param
     * @return
     */
    public static void writeText(String filePath, String content, boolean isAppend) {
        FileOutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            outputStream = new FileOutputStream(filePath,isAppend);
            outputStreamWriter = new OutputStreamWriter(outputStream);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                if(bufferedWriter != null){
                    bufferedWriter.close();
                }
                if (outputStreamWriter != null){
                    outputStreamWriter.close();
                }
                if (outputStream != null){
                    outputStream.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 删除文件
     * @param fileName
     * @return
     */
    public static boolean delete (String fileName){
        try{
            File sourceFile = new File(fileName);
            if(sourceFile.isDirectory()){
                for (File listFile : Objects.requireNonNull(sourceFile.listFiles())) {
                    delete(listFile.getAbsolutePath());
                }
            }
            return sourceFile.delete();
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static List<File> listAll(String path) {
        //获取指定目录下，所有的文件对象和文件名
        File file = new File(path);
        File[] files = file.listFiles();
        assert files != null;
        return Arrays.asList(files);
    }

    /**
     * 如果目录不存在，就创建文件
     * @param dirPath
     * @return
     */
    public static boolean mkdirs(String dirPath) {
        try{
            File file = new File(dirPath);
            if(!file.exists()){
                file.mkdirs();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return existsDirectory(dirPath);
    }

    /**
     * 获取文件编码格式
     * @param fileName
     * @return
     * @throws Exception
     */
    public static String getFileEncoding(String fileName) throws Exception {
        BufferedInputStream bin = new BufferedInputStream(new FileInputStream(fileName));
        int p = (bin.read() << 8) + bin.read();
        String code = null;
        switch (p) {
            case 0xefbb:
                code = "UTF-8";
                break;
            case 0xfffe:
                code = "Unicode";
                break;
            case 0xfeff:
                code = "UTF-16BE";
                break;
            default:
                code = "GBK";
        }

        return code;
    }

    /**
     * 拷贝文件到指定的路径 (使用了自定义的CopyFileUtil工具类)
     * @param fileURL
     * @param targetFile
     */
    public void copy(String fileURL ,String targetFile){
        URLConnection urlconn = null ;
        try {
            URL url = new URL(fileURL);
            urlconn = url.openConnection();
            InputStream in = urlconn.getInputStream();

            File newfile = new File(targetFile);
            FileOutputStream fos = new FileOutputStream(newfile);
            CopyFileUtils.copy(in, fos);//使用了自定义的FileUtil工具类
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 复制文件
     * @param sourceFile
     * @param targetFile
     * @throws IOException
     */
    public static void copyFile(File sourceFile, File targetFile)
            throws IOException {
        // 新建文件输入流并对它进行缓冲
        FileInputStream input = new FileInputStream(sourceFile);
        BufferedInputStream inBuff = new BufferedInputStream(input);
        // 新建文件输出流并对它进行缓冲
        FileOutputStream output = new FileOutputStream(targetFile);
        BufferedOutputStream outBuff = new BufferedOutputStream(output);
        // 缓冲数组
        byte[] b = new byte[1024 * 5];
        int len;
        while ((len = inBuff.read(b)) != -1) {
            outBuff.write(b, 0, len);
        }
        // 刷新此缓冲的输出流
        outBuff.flush();
        // 关闭流
        inBuff.close();
        outBuff.close();
        output.close();
        input.close();
    }

    /**
     * 复制文件夹
     * @param sourceDir
     * @param targetDir
     * @throws IOException
     */
    public static void copyDirectiory(String sourceDir, String targetDir)
            throws IOException {
        // 新建目标目录
        (new File(targetDir)).mkdirs();
        // 获取源文件夹当前下的文件或目录
        File[] file = (new File(sourceDir)).listFiles();
        assert file != null;
        for (File aFile : file) {
            if (aFile.isFile()) {
                // 目标文件
                File targetFile = new File(new File(targetDir)
                        .getAbsolutePath()
                        + File.separator + aFile.getName());
                if (!aFile.getName().contains(".vax"))
                    copyFile(aFile, targetFile);
            }
            if (aFile.isDirectory()) {
                // 准备复制的源文件夹
                String dir1 = sourceDir + "/" + aFile.getName();
                // 准备复制的目标文件夹
                String dir2 = targetDir + "/" + aFile.getName();
                copyDirectiory(dir1, dir2);
            }
        }
    }

    /**
     * 得到文件的扩展名
     * @param f
     * @return
     */
    public static String getFileExtension(File f) {
        if (f != null) {
            String filename = f.getName();
            int i = filename.lastIndexOf('.');
            if (i > 0 && i < filename.length() - 1) {
                return filename.substring(i + 1).toLowerCase();
            }
        }
        return null;
    }

    /**
     * 得到文件名(排除文件扩展名)
     * @param f
     * @return
     */
    public static String getFileNameWithoutExt(File f) {
        if (f != null) {
            String filename = f.getName();
            int i = filename.lastIndexOf('.');
            if (i > 0 && i < filename.length() - 1) {
                return filename.substring(0, i);
            }
        }
        return null;
    }

    /**
     * 改变文件的扩展名
     * @param fileNM
     * @param ext
     * @return
     */
    public static String changeFileExt(String fileNM, String ext) {
        int i = fileNM.lastIndexOf('.');
        if (i >= 0)
            return (fileNM.substring(0, i) + ext);
        else
            return fileNM;
    }

    /**
     * 得到文件的全路径
     * @param filePath
     * @return
     */
    public static String getFileNameWithFullPath(String filePath) {
        int i = filePath.lastIndexOf('/');
        int j = filePath.lastIndexOf("\\");
        int k;
        if (i >= j) {
            k = i;
        } else {
            k = j;
        }
        int n = filePath.lastIndexOf('.');
        if (n > 0) {
            return filePath.substring(k + 1, n);
        } else {
            return filePath.substring(k + 1);
        }
    }



    /**
     * 判断目录是否存在
     * @param strDir
     * @return
     */
    public static boolean existsDirectory(String strDir) {
        File file = new File(strDir);
        return file.exists() && file.isDirectory();
    }

    /**
     * 判断文件是否存在
     * @param strDir
     * @return
     */
    public static boolean existsFile(String strDir) {
        File file = new File(strDir);
        return file.exists();
    }

    /**
     * 强制创建目录
     * @param strDir
     * @return
     */
    public static boolean forceDirectory(String strDir) {
        File file = new File(strDir);
        file.mkdirs();
        return existsDirectory(strDir);
    }

    /**
     * 得到文件的大小
     * @param fileName
     * @return
     */
    public static int getFileSize(String fileName){

        File file = new File(fileName);
        FileInputStream fis = null;
        int size = 0 ;
        try {
            fis = new FileInputStream(file);
            size = fis.available();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return size ;
    }


    public static void main(String[] args) {
        writeText("aaaa", "aaaa", false);
    }
}
