import java.io.*;
import java.util.*;

class SudokuSolver
{
    public static void main(String [] args) throws IOException
    {
        new SudokuSolver().execute();
    }
    
    // [row|col|block](n, i, j) := states whether number n can go into type numbered i position j 
    boolean [][][] row = new boolean[10][9][9];
    boolean [][][] col = new boolean[10][9][9];
    boolean [][][] block = new boolean[10][9][9];
    
    String [] inp = new String [9];
    
    BufferedReader keyin = new BufferedReader(new InputStreamReader(System.in));
    
    void display(boolean [][][] arr)
    {
    	for(int n = 1; n <= 9; n++)
    	{
    		System.out.println("Information for " + n);
    		for(int i = 0; i < 9; i++)
    		{
    			for(int j = 0; j < 9; j++)
    				System.out.print((arr[n][i][j]? 1 : 0));
    			System.out.println();
    		}
    		System.out.println();
    	}
    }
    
    void input() throws IOException
    {
    	for(int i = 1; i <= 9; i++)
    		for(int j = 0; j < 9; j++)
    			for(int k = 0; k < 9; k++)
    				row[i][j][k] = col[i][j][k] = block[i][j][k] = true;
    	
    	//testing...
/*    	for(int i = 0; i < 9; i++)
    	{
    		System.out.println();
    		for(int j = 0; j < 9; j++)
    		{
    			for(int k = 0; k < 9; k++)
    				System.out.print(row[i][j][k]);
    			System.out.println();
    		}
    	}
*/    	
    	String instr;
    	try
    	{
	    	for(int i = 0; i < 9; i++)
	    	{
	    		instr = keyin.readLine();
	    		inp[i] = instr;
	    		if(instr.length() != 9)
	    		{
	    			throw new Exception("Bad Input");
	    		}
	    		for(int j = 0; j < 9; j++)
	    		{
	    			if(instr.charAt(j) == ' ')
	    				continue;
	    			int val = (int)instr.charAt(j) - (int)'0';
	    			System.out.print(val);
	    			if(val <= 0 || val > 9)
	    				throw new Exception("Bad Input");
	    			//i, j'th position = 9.
	    			for(int val1 = 1; val1 <= 9; val1++)
	    			{
	    				if(val1 == val)
	    					continue;
	    				row[val1][i][j] = false;
	    				col[val1][j][i] = false;
	    				
	    				int i1, j1, i2, j2;
	    				i1 = i/3; j1 = j/3;
	    				i2 = i%3; j2 = j%3;
	    				
	    				block[val1][i1*3+j1][i2*3+j2] = false;
	    			}
	    		}
	    	}
	    	
	    	display(row);
    	}
    	catch(Exception e)
    	{
    		System.out.println("Bad Input");
    	}
    }
    
    void execute() throws IOException  // actual main starts here! :)
    {
        input();
        
    }
}
