package misc;

import java.util.Stack;

/**
 * Created by vishalss on 12/19/2015.
 */
public class EvaluatePostFix {

    public static int evaluate(String[] input){

        if(input==null || input.length==0){
            return 0;
        }
        Stack<Integer> stack=new Stack<>();
        int len=input.length;
        int operand1=1;
        int operand2=0;
        for(int i=0;i<len;i++){
            try{
                if(input[i].equals("+")){
                    if(!stack.empty()){
                        operand1=stack.pop();
                        if(!stack.empty()){
                            stack.push(stack.pop()+operand1);
                        }
                        else{
                            System.out.println("Error: Invalid expression.");
                            return 0;
                        }
                    }
                    else{
                        System.out.println("Error: Invalid expression.");
                        return 0;
                    }
                }
                else if(input[i].equals("-")){
                    if(!stack.empty()){
                        operand1=stack.pop();
                        if(!stack.empty()){
                            stack.push(stack.pop()-operand1);
                        }
                        else{
                            System.out.println("Error: Invalid expression.");
                            return 0;
                        }
                    }
                    else{
                        System.out.println("Error: Invalid expression.");
                        return 0;
                    }
                }
                else if(input[i].equals("*")){
                    if(!stack.empty()){
                        operand1=stack.pop();
                        if(!stack.empty()){
                            stack.push(stack.pop()*operand1);
                        }
                        else{
                            System.out.println("Error: Invalid expression.");
                            return 0;
                        }
                    }
                    else{
                        System.out.println("Error: Invalid expression.");
                        return 0;
                    }
                }
                else if(input[i].equals("/")){
                    if(!stack.empty()){
                        operand1=stack.pop();
                        if(operand1==0){
                            System.out.println("Error: Divide by 0");
                            return 0;
                        }
                        else if(!stack.empty()){
                            stack.push(stack.pop()/operand1);
                        }
                        else{
                            System.out.println("Error: Invalid expression.");
                            return 0;
                        }
                    }
                    else{
                        System.out.println("Error: Invalid expression.");
                        return 0;
                    }
                }
                else {
                    stack.push(Integer.parseInt(input[i]));
                }
            }
            catch (NumberFormatException E){
                System.out.println("Error: Invalid expression.");
                return 0;
            }
        }
        if(!stack.empty()){
            return  stack.pop();
        }
        else{
            System.out.println("Error: Invalid expression.");
            return 0;
        }
    }

    public static void main(String[] args) {
        String[] input={"26","3","1","*","+","9","-"};
        int result=EvaluatePostFix.evaluate(input);
        System.out.println("result= "+result);
    }
}
