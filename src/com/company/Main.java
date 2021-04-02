package com.company;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        System.out.println("_____________________TASK_1_____________________");
        List<Integer> a = getIntegersFromList(Arrays.asList(1,2,'a','b',"aasf",'1',"123",231));
        System.out.println("Integers from list:"+a);
        System.out.println("_____________________TASK_2_____________________");
        String s = "sTreSS";
        System.out.println("Input:"+s);
        char character = first_non_repeating(s);
        System.out.println("First non repeating character:"+character);
        System.out.println("_____________________TASK_3_____________________");
        System.out.println("Digital root:"+digitalRoot(493193));
        System.out.println("_____________________TASK_4_____________________");
        ArrayList<Integer> array = new ArrayList<Integer>();
        array.add(1);
        array.add(3);
        array.add(6);
        array.add(2);
        array.add(2);
        array.add(0);
        array.add(4);
        array.add(5);
        System.out.println("Input:"+array);
        System.out.println("For loop");
        System.out.println("Number of pairs:"+getPairsCount(array,5));
        System.out.println("With streams");
        System.out.println("Number of pairs:"+getPairsCountStream(array,5));
        System.out.println("_____________________TASK_5_____________________");
        System.out.println("Sorted:"+extractAndSort("CORWILL,ALFRED;CORWILL,FRED;CORWILL,RAPHAEL;TORNBULL,BARNEY;TORNBULL,BETTY;TORNBULL,BJON;CORWILL,WILFRED;"));
        System.out.println("_____________________EXTRA_TASK_1_____________________");
        System.out.println("Next bigger:"+extra1FindNext(2017));
        System.out.println("_____________________EXTRA_TASK_2_____________________");
        System.out.println(extra2IntegerToStringIP(2149583361L));
        System.out.println(extra2IPtoBinary("255.128.145.25"));
    }

    public static List<Integer> getIntegersFromList(List<Object> inputList)
    {
        System.out.println("Input:"+inputList);
     int i =0;
     List<Integer> outputList = new ArrayList<Integer>();
     while (i<inputList.size())
     {
         if (inputList.get(i) instanceof Integer && (int) inputList.get(i)>0)
         {
             outputList.add((int)inputList.get(i));
         }
        i++;
     }
     return outputList;
    }
    public static char first_non_repeating(String inputString)
    {

      String lower_case = inputString.toLowerCase();
      for(int i =0;i<inputString.length();i++)
      {
          char toCount = lower_case.toCharArray()[i];
          int counter = 0;
          for(char c:lower_case.toCharArray())
          {
              if (c == toCount)
              {
                  counter++;
              }

          }
          if (counter==1)
          {
              return inputString.charAt(i);
          }
      }
    return '\0';
    }

    public static int digitalRoot(int inputInteger)
    {
        System.out.println("Input:"+inputInteger);
        return 1+(inputInteger-1)%9;
    }

    public static int getPairsCount(ArrayList<Integer> array,int sum)
    {
        int count = 0;
        for(int i =0;i<array.size();i++)
        {
            for (int j =i+1;j<array.size();j++)
            {
                if(array.get(i)+array.get(j)==sum)
                    count++;
            }
        }
        return count;
    }

    public static int getPairsCountStream(ArrayList<Integer> array,int sum)
    {
        HashMap<Integer,Integer> counter = new HashMap<>();
        array.stream().forEach(element ->{
            if(!counter.containsKey(element)){
                counter.put(element,0);
            }
            counter.put(element,counter.get(element)+1);
        });
        int twice_counter = array.stream()
                .filter(element->counter.get(sum-element)!=null)
                .mapToInt(element ->counter.get(sum-element)).sum();
        return twice_counter/2;
    }



    private static FirstNameAndLastName extractPersonFirstNameAndLastName(String personName) {
        String[] split = personName.split(",");
        String firstName = split[1];
        String lastName = split[0];
        return new FirstNameAndLastName(firstName, lastName);
    }

    public static String extractAndSort(String s) {
        System.out.println("Input:"+s);
        List<FirstNameAndLastName> toSort = new ArrayList<>();
        for (String s1 : s.split(";")) {
            String toUpperCase = s1.toUpperCase();
            FirstNameAndLastName firstNameAndLastName = extractPersonFirstNameAndLastName(toUpperCase);
            toSort.add(firstNameAndLastName);
        }
        toSort.sort(Comparator.comparing(FirstNameAndLastName::getLastName).thenComparing(FirstNameAndLastName::getFirstName));
        StringBuilder sb = new StringBuilder();
        for (FirstNameAndLastName firstNameAndLastName : toSort) {
            String format = firstNameAndLastName.format();
            sb.append(format);
        }
        return sb.toString();
    }

    private static class FirstNameAndLastName {
        private final String firstName;
        private final String lastName;

        public FirstNameAndLastName(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String format() {
            return String.format("(%s, %s)", this.lastName, this.firstName);
        }
    }
    static void swap(char ar[], int i, int j)
    {
        char temp = ar[i];
        ar[i] = ar[j];
        ar[j] = temp;
    }
    public static int extra1FindNext(int number)
    {
        System.out.println("Input:"+number);
        char[] array=(""+number).toCharArray();
        int i;
        for(i = array.length-1;i>0;i--)
        {
            if(array[i]>array[i-1])
            {
                break;
            }
        }
        if(i==0)
        {
           return -1;
        }
        else {
            int x=array[i-1],min=i;
            for(int j = i+1;j<array.length;j++)
            {
                if(array[j]>x && array[j]<array[min])
                {
                    min=j;
                }
            }
            swap(array,i-1,min);
            Arrays.sort(array,i,array.length);
            return Integer.parseInt(String.valueOf(array));

        }
    }

    public static String extra2IntegerToStringIP(long ip) {
        System.out.println("Input:"+ip);
        return ((ip >> 24 ) & 0xFF) + "." +

                ((ip >> 16 ) & 0xFF) + "." +

                ((ip >>  8 ) & 0xFF) + "." +

                ( ip        & 0xFF);
    }
    public static String extra2IPtoBinary(String ip)
    {
        System.out.println("Input:"+ip);
        String[] splitted = ip.split("\\.");
        String binary  = new String();
        for(String s :splitted)
        {
            String i = Integer.toBinaryString(Integer.parseInt(s));
            binary+=String.format("%8s",i).replaceAll(" ","0");
            binary+=".";
        }
        binary = binary.substring(0,binary.length()-1);
        return binary;
    }

}


