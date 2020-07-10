
package scc;

import com.sun.xml.internal.ws.util.StringUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;
import java.util.Scanner; 
/**
 *
 * @author Engy Samy
 */


public class Scc {
    
     enum States {Start,Num,Comment,ID,Assgin,Done};
    
    public static void ch(String str)
    {
        String [] symb =new String[]{"+","-","*","/","=","<","(",")",";",":="};
        String [] symb1 =new String[]{"Plus","minus","Multiply","slach","equal","bigger than","left bracket","right bracket","semi coln","assginment op"};
         String [] done =new String[str.length()];
         String [] type =new String[str.length()];
         String [] type1 =new String[str.length()];
         String [] resword =new String[]{"if","then","else","end","repeat","until","read","write"};
         int d=0;
         int index=0;
         boolean z=false;
         boolean en=false;
         boolean number=false;
         States stat=States.Start;
         String word="";
         String num="";
         
         while(stat==States.Start &&index<str.length())
         {

             
             while(index<str.length()&&isDigit(str.charAt(index))&& stat!=stat.Done )
             {
                 num=num+str.charAt(index);
                 stat=States.Num;
                   done[d]=num;
                   type[d]="Number";
                   d++;
                 number=true;
                 index++;       
             }
             if(number)
             {
                 stat=States.Done;
                 num="";
             }
             if(index<str.length()&&stat!=States.Done&&str.charAt(index)=='{')
             {
                  
                     index++;

                     stat=States.Comment;
             }
             if(index<str.length()&&stat==States.Comment)
             {
                 String comm="";
                 while(str.charAt(index)!='}')
                 {
                     comm=comm+str.charAt(index);
                     index++;
                 }
                 if(str.charAt(index)=='}')
                 {
                    
                     d++;
                     index++;
      
                     stat=States.Done;
                 }
             }
           while(index<str.length()&&isLetter(str.charAt(index)))
            {
             word=word+str.charAt(index);
             stat=States.ID;
             index++;    
            }
             if(stat==States.ID)
             {
                for(int i=0;i<resword.length;i++)
                {
                    if(word.equalsIgnoreCase(resword[i]))
                    {
                        done[d]=word;
                        word="";
                        type[d]="Reserved Word";
                        en=true;
                        d++;
                        stat=States.Done;
                    }
                }
                if(!en)
                {
                    done[d]=word;
                    word="";
                    type[d]="Identifier";
                    d++;
                    stat=States.Done;
                }    
             }
             while(index<str.length()&&stat!=States.Done && str.charAt(index)==' ' )
             {
                 stat=States.Done;
                 index++;
             }
             
             int sym=0;
            while(index<str.length()&&stat!=States.Done)
            {
                String s=Character.toString(str.charAt(index));
                for(int i=0;i<symb.length;i++)
                {  
                    if(s.equals(symb[i]))
                    {
                        done[d]=s;
                        type[d]="Symbol( "+symb1[i]+")";
                       
                       
                        stat=States.Assgin;
                        d++;
                        index++;
                    }
                }
                 if(s.equals(":")&&str.charAt(index+1)=='=')
                    {
                            done[d]=":=";
                            type[d]="Symbol(" +symb1[9]+")";
                            stat=States.Assgin;
                            d++;
                            index++;
                            index++;
                     }
                stat=States.Done;
            }
            en=false;
            number=false;
            stat=States.Start;
         }
         //System.out.println(index);
         System.out.println("Token-------------->>>----------Type");
         for(int j=0;j<d;j++)
         {
             System.out.println(done[j]+"-------------->>--------------"+type[j]);
         }
         
        
    } 
         
    public static void scann  ()throws Exception
    {
         
          File file =  new File("E:\\f.txt"); 
   // Scanner sc = new Scanner(file); 
        BufferedReader br=new BufferedReader (new FileReader(file));
        String ss;
int len=0;
String sen="";
        while ((ss = br.readLine())!= null) 
        {
            sen=sen+ss;
        }
        //System.out.println(sen);
        //System.out.println(sen.length());
        ch(sen);
        
    }

    public static void main(String[] args) throws Exception  {
       scann();
    }
    
}

