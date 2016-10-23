import java.util.ArrayList;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author Lingling Hu
 * @version 1.0
 */
public class window
{
	private String sequence;
	private int size;
	private int start;
	private int end;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static ArrayList<String> seq = new ArrayList();
	public int num[];
	public int sum = 0;
	
	public window()
	{
		super();
	}
	
	public window(String sequence)
	{
		super();
		this.sequence = sequence;
	}
	
	/*
	 * @return the sequence
	 */
	public String getSequence()
	{
		return sequence;
	}
	
	/*
	 * @return the size
	 */
	public int getSize()
	{
		return size;
	}
	
	public void setSize(int size)
	{
		this.size = size;
	}
	
	/*
	 * @return the start
	 */
	public int getStart()
	{
		return start;
	}
	
	/*
	 * @return the end
	 */
	public int getEnd()
	{
		return end;
	}
	
	/*
	 * @initialize the window
	 */
	@SuppressWarnings("static-access")
	public void initialize()
	{
		this.start = -1;
		this.end = this.size - 2;
		
		seq.clear();
		testList tlist = new testList(size);
		seq = tlist.seq;
		num = new int[seq.size()];
		sum = 0;
		// System.out.println(num.length);
	}
	
	public void slide()
	{
		this.start++;
		this.end++;
		// System.out.print(size);
		String str = this.sequence.substring(start, end + 1);
		
		search(str);
	}
	
	public void search(String str)
	{
		for (int i = 0; i < seq.size(); i++)
		{
			
			if (str.equals(seq.get(i).toString()))
			{
				num[i]++;
				sum++;
			}
		}
	}
	
}
