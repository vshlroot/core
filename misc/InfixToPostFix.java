package misc;

import sort.Sort;

import java.util.Stack;

/**
 * Created by vishalss on 12/20/2015.
 */
public class InfixToPostFix {

//===============================================================================
// Converts an infix expression to postfix expression.
//===============================================================================
    public static String[] infixToPostFix2(String[] input){
        if(input.length==0){
            return null;
        }
        Stack<String> operatorStack=new Stack<>();
        String[] result=new String[input.length];
        int j=0;
        String str;
        int precedence=0;
        // scan the input from left to right
        for(int i=0;i<input.length;i++){
            // if input is an operator
            if(input[i].equals("/") || input[i].equals("*") || input[i].equals("+") || input[i].equals("-")|| input[i].equals("(") || input[i].equals(")") || input[i].equals("^")){
                precedence=getPrecedence(input[i]); //get precedence of the operator
                // if stack is empty then push the operator
                if(operatorStack.empty()){
                    operatorStack.push(input[i]);
                }
                // if it's a bracket close then pop all the operators and add to result
                else if( input[i].equals(")")){
                    while(!operatorStack.empty() && !(operatorStack.peek().equals("("))){
                        result[j]=operatorStack.pop();
                        j++;
                    }
                    // Pop the ( from the stack, we don't need it anymore.
                    operatorStack.pop();
                }
                // if the current operator has higher precedence than the operator on top of the stack then push it.
                else if(precedence>getPrecedence(operatorStack.peek())){
                    operatorStack.push(input[i]);
                }
                // if current precedence <= top operator on stack
                else{
                    // pop till precedence of current operator becomes > top of stack
                    while(!operatorStack.empty() && !(operatorStack.peek().equals("(")) && getPrecedence(operatorStack.peek())>=precedence){
                        result[j]=operatorStack.pop();
                        j++;
                    }
                    // push the current operator on stack.
                    operatorStack.push(input[i]);
                }
            }
            // if this is an operand then add to result
            else{
                result[j]=input[i];
                j++;
            }

        }
        // Push all operators left on the stack.
        while(!operatorStack.empty()){
            result[j]=operatorStack.pop();
            j++;
        }
        return result;
    }

//===============================================================================
// Returns the precedence of the operator
//===============================================================================
    public static int getPrecedence(String str){
        if(str==null){
            return -1;
        }
        else if(str.equals("(")){
            return 5;
        }
        else if(str.equals("^")){
            return 4;
        }
        else if(str.equals("/")){
            return 3;
        }
        else if(str.equals("*")){
            return 3;
        }
        else if(str.equals("+")){
            return 2;
        }
        else if(str.equals("-")){
            return 2;
        }
        else{
            return -1;
        }
    }

    public static void main(String[] args) {
        //a+b*(c^d-e)^(f+g*h)-i
        //abcd^e-fgh*+^*+i-
        //String[] input={"26","+","1","-","9","+","8"};

        //String[] input={"(","a","+","b",")","^","c"};
        String[] input={"a","+","b","*","(","c","^","d","-","e",")","^","(","f","+","g","*","h",")","-","i"};

        Sort.printArray(input,1,"");
        String[] result=InfixToPostFix.infixToPostFix2(input);


        for(int i=0;i<result.length;i++){
            if(result[i]!=null)
            System.out.print(result[i]);
        }


    }
}
