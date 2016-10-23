import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class testList
{
	
	List<String> sourceList;// 源数据
	public List<List<String>> targetList = new LinkedList<List<String>>(); // 生成的所有排列存放的容器
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static ArrayList<String> seq = new ArrayList();
	
	public testList(int listLength)
	{
		sourceList = new ArrayList<String>();
		sourceList.add("G");
		sourceList.add("A");
		sourceList.add("V");
		sourceList.add("L");
		sourceList.add("I");
		sourceList.add("P");
		sourceList.add("F");
		sourceList.add("Y");
		sourceList.add("W");
		sourceList.add("S");
		sourceList.add("T");
		sourceList.add("C");
		sourceList.add("M");
		sourceList.add("N");
		sourceList.add("Q");
		sourceList.add("D");
		sourceList.add("E");
		sourceList.add("K");
		sourceList.add("R");
		sourceList.add("H");
		
		List<List<String>> targetList = productList(listLength);
		String str = "";
		for (int i = 0; i < targetList.size(); i++)
		{
			str = "";
			for (int j = 0; j < targetList.get(i).toArray().length; j++)
			{
				str += targetList.get(i).get(j).toString();
				// System.out.println(targetList.get(i).get(j).toString());
				
			}
			seq.add(str);
		}
		
	}
	
	/**
	 * @param args
	 */
	
	public List<List<String>> productList(int listLength)
	{ // System.out.println(sourceList.size());
		if (listLength > 0)
		{
			for (int i = 0; i < sourceList.size(); i++)
			{
				List<String> childList = new LinkedList<String>();
				addEle(childList, i, listLength);
			}
		}
		return this.targetList;
	}
	
	public void addEle(List<String> currentList, int index, int listLength)
	{
		currentList.add(sourceList.get(index).toString());
		if (currentList.size() < listLength)
		{
			for (int i = 0; i < sourceList.size(); i++)
			{
				List<String> childList = new LinkedList<String>();
				childList.addAll(currentList);
				addEle(childList, i, listLength);
			}
		}
		else if (currentList.size() == listLength)
		{
			targetList.add(currentList);
		}
	}
}
