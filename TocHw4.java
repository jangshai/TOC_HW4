import java.io.*;
import java.net.*;
import java.util.ArrayList;

import org.json.*;
public class TOC_HW4 {

	public static void main(String[] argv) throws IOException, JSONException , NullPointerException {
		try{
		// TODO Auto-generated method stub
		    String url=new String(argv[0]);
			URL myurl = new URL(url);
			URLConnection myconnection = myurl.openConnection();
			myconnection.connect();
			BufferedReader BR = new BufferedReader(new InputStreamReader(myurl.openStream(),"UTF-8"));
			String temp;
			StringBuilder builder = new StringBuilder();
			while ((temp = BR.readLine()) != null)
			{
				builder.append(temp);
				//System.out.println(temp);
			}
	        String json = builder.toString();
			JSONTokener jsontokener = new JSONTokener(json);
			JSONArray jsonarray = new JSONArray(jsontokener);
			ArrayList<String> roadarray = new ArrayList<String>();
			ArrayList<Integer> numinroad = new ArrayList<Integer>();
			for(int i=0;i<jsonarray.length();i++)
			{
				
				JSONObject jsonob = jsonarray.getJSONObject(i);
				String temp1 = jsonob.getString("�g�a�Ϭq��m�Ϋت��Ϫ��P");
				int k=0;
				
				if(temp1.contains("��"))
				{
					temp1=temp1.substring(0,temp1.indexOf("��")+1);
					k=1;
					//System.out.println(temp1);
				}
				else if(temp1.contains("��"))
				{
					temp1=temp1.substring(0,temp1.indexOf("��")+1);
					k=1;
					//System.out.println(temp1);
				}
				else if(temp1.contains("�j�D"))
				{
					temp1=temp1.substring(0,temp1.indexOf("�j�D")+2);
					k=1;
					//System.out.println(temp1);
				}
				else if(temp1.contains("��"))
				{
					temp1=temp1.substring(0,temp1.indexOf("��")+1);
					k=1;
					//System.out.println(temp1);
				}
				if(k==1)
				{
					if(roadarray.contains(temp1))
					{
						int temp3=roadarray.indexOf(temp1);
						ArrayList<Integer> months = new ArrayList<Integer>();
						
						for(int j=0;j<jsonarray.length();j++)
						{
							
							JSONObject aaa = jsonarray.getJSONObject(j);
								int compare=aaa.getInt("����~��");
								String temp5 = aaa.getString("�g�a�Ϭq��m�Ϋت��Ϫ��P");
								//System.out.println(compare);
								
									if(temp5.contains(temp1))
									{
										if(months.contains(compare))
										{}
										else
										months.add(compare);
									//int temp4=numinroad.get(temp3);
									//temp4=temp4+1;
									//numinroad.set(temp3, temp4);
								
									}
						
						}
						numinroad.set(temp3,months.size());
											
					}
					else 
					{
						roadarray.add(temp1);
						int temp3=roadarray.indexOf(temp1);
						numinroad.add(temp3, 0);
					}
					

				}
			}
			//for(int i=0;i<roadarray.size();i++)
			//{
			//	System.out.println(i+":"+roadarray.get(i)+"  "+numinroad.get(i));
			//}
			ArrayList<String> aa = new ArrayList<String>();
			int maxnum=0;
			for(int i=0;i<roadarray.size();i++)
			{
				if(maxnum<numinroad.get(i))
				{
					maxnum=numinroad.get(i);
				}
			}
			//System.out.println("max:"+maxnum);
			for(int i=0;i<roadarray.size();i++)
			{
				if(numinroad.get(i)==maxnum)
				{
					aa.add(roadarray.get(i));
					//System.out.println(roadarray.get(i));
				}
			}
			for(int i=0;i<aa.size();i++)
			{
				int max=0;
				int min=0;
				for(int j=0;j<jsonarray.length();j++)
				{
					JSONObject jsonob = jsonarray.getJSONObject(j);
					String temp1 = jsonob.getString("�g�a�Ϭq��m�Ϋت��Ϫ��P");
					int temp7 = jsonob.getInt("�`����");
					//System.out.println(temp1);
					if(temp1.contains(aa.get(i)))
					{
						//System.out.println(j+"  "+temp7);
						if(max<temp7)
						max=temp7;
						if(min==0)
						min=temp7;
						if(min>temp7)
						min=temp7;
							
						
					}
				}
				System.out.println(aa.get(i)+",�̰������"+max+",�̧C�����"+min);
			}
				
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}

}

