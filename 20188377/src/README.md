| 这个作业属于哪个课程 | 软工-2018级计算机1班                                         |
| -------------------- | ------------------------------------------------------------ |
| 这个作业要求在哪里   | https://edu.cnblogs.com/campus/zswxy/computer-science-class2-2018 |
| 这个作业的目标       | 实践学习软件工程                                             |
| 学号                 | 20188377                                                     |
> **主函数**
```
import java.io.;
import java.util.;

public class WordCount {
private static final File ROOT_File =new File("D:\新建文件夹");
private static int count = 0;
private static Map<String,Integer> wordCount = new HashMap<>();

public static void main(String [] args) throws Exception{
    String intputFileName =  args[0];
    String outputFileName = args[1];
    File inputFile = new File(ROOT_File,intputFileName);
    File outputFile = new File(ROOT_File,outputFileName);

    // 判断是否存在:
    if(inputFile.exists()){
        doCheck(inputFile);
    }else{
        throw new RuntimeException("error");
    }
    PrintStream stream = new PrintStream(new FileOutputStream(outputFile));
    System.setOut(stream);
    show();
    System.out.println("单词数:"+obtainTotalWords());
    System.out.println("行数:"+count);
    System.out.println("字符数:"+(inputFile.length()));
}
```
```
public  static void doCheck(File inputFile) throws Exception{
    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
    String line = null;
    while(null!=(line=br.readLine())){
        incrLine();
        // 分析每一行。
        analysis(line);
    }
}
```
>**获取行数**
```
public static void show(){
    Set<Map.Entry<String, Integer>> entries = wordCount.entrySet();
    // 排序
    ArrayList<String> words = new ArrayList<>();
    for (Map.Entry<String, Integer> entry : entries) {
        words.add(entry.getValue()+"#"+entry.getKey());
    }

    // 排序
    Collections.sort(words);
    words.forEach(obj->{
        String[] split = obj.split("#");
        String str = split[1]+": "+split[0];
        System.out.println(str);
    });
}

public static void incrLine(){
    // 行数加1
    count++;
}
```

>**获取总单词数**
```
//总单词数
public static long obtainTotalWords(){
    long sum = 0;
    Set<Map.Entry<String, Integer>> entries = wordCount.entrySet();
    for (Map.Entry<String, Integer> entry : entries) {
        sum+=entry.getValue();
    }
    return sum;
}
```
>**统计单词及其出现的个数**
```
// 得到每一个单词以及次数, 并且记录到Map集合中
public static void analysis(String line){
    String [] words = line.split(" ");
    for(int i=0;i<words.length;i++){
        String word = words[i].trim();
        word = word.toLowerCase();
        word = word.contains(",")||word.contains(".")?word.substring(0,word.length()-1):word;
        if(word.length()>=4&&isWord(word.substring(0,4))){
            if(wordCount.containsKey(word)){
                Integer count = wordCount.get(word);
                count++;
                wordCount.put(word,count);
            }else{
                wordCount.put(word,1);
            }
        }
    }
}

public static boolean isWord(String word){
    for(int i=0;i<word.length();i++){
        if(word.charAt(i)>=97 && word.charAt(i)<=122){
            continue;
        }else{
            return false;
        }
    }
    return true;
}
```
![](https://img2020.cnblogs.com/blog/1580648/202104/1580648-20210401213334886-1417741880.png)
![](https://img2020.cnblogs.com/blog/1580648/202104/1580648-20210401213511082-1630825549.png)