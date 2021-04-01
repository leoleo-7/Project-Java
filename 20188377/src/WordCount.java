import java.io.;
import java.util.;

public class WordCount {
private static final File ROOT_File =new File("D:\�½��ļ���\"); //�ļ�·��
private static int count = 0;
private static Map<String,Integer> wordCount = new HashMap<>();

public static void main(String [] args) throws Exception{
    String intputFileName =  args[0];
    String outputFileName = args[1];
    File inputFile = new File(ROOT_File,intputFileName);
    File outputFile = new File(ROOT_File,outputFileName);

    // �ж��Ƿ����:
    if(inputFile.exists()){
        doCheck(inputFile);
    }else{
        throw new RuntimeException("error");
    }
    PrintStream stream = new PrintStream(new FileOutputStream(outputFile));
    System.setOut(stream);
    show();
    System.out.println("������:"+obtainTotalWords());
    System.out.println("����:"+count);
    System.out.println("�ַ���:"+(inputFile.length()));


}

public  static void doCheck(File inputFile) throws Exception{
    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
    String line = null;
    while(null!=(line=br.readLine())){
        incrLine();
        // ����ÿһ�С�
        analysis(line);
    }
}

public static void show(){
    Set<Map.Entry<String, Integer>> entries = wordCount.entrySet();
    // ����
    ArrayList<String> words = new ArrayList<>();
    for (Map.Entry<String, Integer> entry : entries) {
        words.add(entry.getValue()+"#"+entry.getKey());
    }

    // ����
    Collections.sort(words);
    words.forEach(obj->{
        String[] split = obj.split("#");
        String str = split[1]+": "+split[0];
        System.out.println(str);
    });
}

public static void incrLine(){
    // ������1
    count++;
}

//�ܵ�����
public static long obtainTotalWords(){
    long sum = 0;
    Set<Map.Entry<String, Integer>> entries = wordCount.entrySet();
    for (Map.Entry<String, Integer> entry : entries) {
        sum+=entry.getValue();
    }
    return sum;
}

// �õ�ÿһ�������Լ�����, ���Ҽ�¼��Map������
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
}