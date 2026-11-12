/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Viva;

import java.util.Scanner;

/**
 *
 * @author lauhiapzhi
 */
public class Q1 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        
        System.out.print("Enter Original Phrase: ");
        String phrase = in.nextLine();
        
        char[] phrases = new char[phrase.length()];
        for(int i=0;i<phrase.length();i++){
            phrases[i] = phrase.charAt(i);
        }
        
        int sum = 0;
        for(int i=0;i<phrases.length;i++){
            if(phrases[i]>='0'&&phrases[i]<='9'){
                sum += phrases[i] - '0';
            }
        }
        int key = sum%26;
        if(key==0){
            key=13;
        }
        
        int vovelcount=0;
        for(int i=0;i<phrases.length;i++){
            if(phrases[i]=='A'||phrases[i]=='E'||phrases[i]=='I'||phrases[i]=='O'||phrases[i]=='U'){
                vovelcount++;
            }
        }
        
        char[] encoded = new char[phrase.length()+vovelcount];
        int n=0;
        
        for(int i=0;i<phrases.length;i++){
            if(phrases[i]>='a'&&phrases[i]<='z'){
                char shifted = (char)(phrases[i]+key);
                if(shifted > 'z'){
                    shifted = (char)(shifted-26);
                }
                encoded[n] = shifted;
                n++;
            }else if(phrases[i]>='A'&&phrases[i]<='Z'){
                if(phrases[i]=='A'||phrases[i]=='E'||phrases[i]=='I'||phrases[i]=='O'||phrases[i]=='U'){
                    encoded[n] = '*';
                    n++;
                    encoded[n] = (char)(phrases[i]-'A'+'a');
                    n++;
                }else{
                    char next = (char)(phrases[i]-'A'+'a'+1);
                    if(next > 'z'){
                        next = (char)(next-26);
                    }
                    encoded[n] = next;
                    n++;
                }
            }else if(phrases[i]>='0'&&phrases[i]<='9'){
                int d = (phrases[i] - '0');
                encoded[n] = (char)(((d*d)%10) + '0');
                n++;
            }else if(phrases[i]==' '){
                encoded[n] = '.';
                n++;
            }else if(phrases[i]=='!'){
                encoded[n] = '#';
                n++;
            }else if(phrases[i]=='@'){
                encoded[n] = '$';
                n++;
            }else if(phrases[i]=='#'){
                encoded[n] = '%';
                n++;
            }else if(phrases[i]=='$'){
                encoded[n] = '!';
                n++;
            }else if(phrases[i]=='%'){
                encoded[n] = '@';
                n++;
            }else{
                encoded[n] = phrases[i];
                n++;
            }
        }
        System.out.println("Shift Key: "+key);
        System.out.print("Before Reversal: ");
        for(int i=0;i<n;i++){
            System.out.print(encoded[i]);
        }
        System.out.println();
        System.out.print("Encoded Password: ");
        for(int i=n-1;i>=0;i--){
            System.out.print(encoded[i]);
        }
        System.out.println();
    }
}
