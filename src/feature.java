import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

public class feature
{
	public static void main(String[] args) throws Exception
	{
		if (args.length != 5)
		{
			System.out.println("1. Usage: java -jar n-gram_pro.jar N input.fasta output.libsvm method label");
			System.out.println("where N is N-gram param, method is 1 or 2, label is your libsvm label, such as 1 or -1");
			System.out.println("2. Example: java -jar n-gram_pro.jar 2 input.fasta output.libsvm 1 1");
			System.exit(0);
		}
		int N = Integer.parseInt(args[0]);
		String fileinput = args[1];
		String fileoutput = args[2];
		int method = Integer.parseInt(args[3]);
		String label = args[4];
		/*int N = 2;
		String fileinput = "GABA_CR.txt";
		String fileoutput ="demo2.txt";
		String label = "1";
		int method = 2;*/
		
		ArrayList<String> resultList = gen_feature(N, fileinput, method);
		BufferedWriter bwBufferedWriter = new BufferedWriter(new FileWriter(fileoutput));
		for (int i=0; i<resultList.size(); i++) {
			String lineString = resultList.get(i);
			bwBufferedWriter.write(label+" "+lineString.substring(0, lineString.length()-1)+"\n");
		}
		
		bwBufferedWriter.close();
		System.out.println("N_gram_pro finished.");
		
	}
	
	
	public static ArrayList<String> gen_feature(int i, String fileinput, int method) throws IOException {
		ArrayList<String> result = new ArrayList<String>();
		
		int sum = 0;
		int N[] = new int[i];
		double C[] = new double[i];
		
		
		for (int m = 1; m <= i; m++)
		{
			N[m - 1] = (int) Math.pow(20, m);
			sum += N[m - 1];
		}
		for (int m = 0; m < i; m++)
		{
			C[m] = N[m] / (double) sum;
			
		}
		double f[] = new double[sum];
		String sequence;
		FileReader fr = new FileReader(fileinput);
		BufferedReader br = new BufferedReader(fr);
		String str = br.readLine();
		while(br.ready())
		{	
			if(str.charAt(0)=='>')
			{
				//String key=str.substring(1,str.indexOf(" "));
				str = br.readLine();
				while (str!=null && str.equals("")) {
					str = br.readLine();
				}
				StringBuffer seq = new StringBuffer();
				while (str!=null && str.charAt(0)!= '>') 
	    		{
	    			seq.append(str);
	    			if (br.ready()) 
	    			{
	    				str = br.readLine();
	    				while (str!=null && str.equals("")) {
	    					str = br.readLine();
	    				}
	    			} 
	    			else 
	    			{
	    				break;
	    			}
	    		}
				sequence=seq.toString().toUpperCase();
				window win = new window(sequence);
				int T[] = new int[i];
				int t[] = new int[sum];
				
				String lineSring2 = "";
				int index = 0;
				for (int m = 1; m <= i; m++)
				{
					win.setSize(m);
					win.initialize();
					
					while (win.getEnd() != sequence.length() - 1)// 窗口为i时进行移动扫描，扫描结果放进seq和num数组中
					{
						win.slide();
					}
					for (int j = 0; j < N[m - 1]; j++)// 位ti赋值
					{
						t[j] = win.num[j];
						T[m - 1] += t[j];
					}
					
					String lineString = "";
					if (method == 1)
					{
						for (int j = 0; j < N[m - 1]; j++)// 位ti赋值
						{
							f[j] = (double) t[j] / (double) T[m - 1] * C[m - 1];
							BigDecimal b = new BigDecimal(f[j]);
							double num = b.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
							if (num == 0) {
								++index;
							} else {
								lineString += String.valueOf(++index)+":"+String.valueOf(num)+" ";
							}
						}
					}
					else
					{
						for (int j = 0; j < N[m - 1]; j++)// 位ti赋值
						{
							double num = t[j];
							if (num == 0) {
								++index;
							} else {
								lineString += String.valueOf(++index)+":"+String.valueOf(num)+" ";
							}
						}
					}
					lineSring2 += lineString;
				}
				result.add(lineSring2);
			}
			
		}
		br.close();
		return result;
	}
	
}
