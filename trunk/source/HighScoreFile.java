package project;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
//import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class HighScoreFile {
	public ArrayList<String> readHighScore(){
		ArrayList<String> line = new ArrayList<String>();
		try {
			File file = new File("test.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
//			StringBuffer stringBuffer = new StringBuffer();
			
			while (bufferedReader.ready()) 
			{
				String s = bufferedReader.readLine();
				line.add(s);
			}
			fileReader.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		return line;
	}
	
	public void changeScore(int newScore) 
	{
		boolean check = isHighScore(newScore);
		boolean found = false;
		int score2 = 0, score3=0;
		ArrayList<String> line = new ArrayList<String>();
		line = readHighScore();
		if(check)
		{
			for(int i = 0; i< line.size() && !found; i++)
			{
				if(Integer.parseInt(line.get(i))<newScore)
				{
					score2 = Integer.parseInt(line.get(i));
					line.remove(i);
					line.add(i, String.valueOf(newScore));
					for(int j = i+1; j < line.size() - 1; j++)
					{
						score3 = Integer.parseInt(line.get(j));
						line.remove(j);
						line.add(j, String.valueOf(score2));
						score2 = score3;
					}
					found = true;
				}
			}
		
			try
			{
				PrintWriter pw = new PrintWriter("test.txt");
				pw.close();
				BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
                    "test.txt"), true));
		 
				for (int i = 0; i < line.size(); i++) {
					bw.write(line.get(i));
					bw.newLine();
				}
		 
				bw.close();
			}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	}
	public boolean isHighScore(int newScore)
	{
		boolean check =false;
		ArrayList<String> line = new ArrayList<String>();
		line = readHighScore();
		for(int i = 0; i< line.size()-1 && !check; i++)
		{
			if(Integer.parseInt(line.get(i)) < newScore)
			{
				check = true;
			}
		}
		return check;
	}
	public ArrayList<Integer> getHighScores()
	{
		ArrayList<String> line = new ArrayList<String>();
		line = readHighScore();
		
		ArrayList<Integer> scoreList = new ArrayList<Integer>();
		for(int i = 0; i < 4; i++)
		{
			scoreList.add((Integer.parseInt(line.get(i))));
		}
		return scoreList;
	}
}