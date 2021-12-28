package PolishNotation;


import java.util.Stack;

public class NotationHandler {
    private String postfixFormUsingStack = "";
    private String postfixFormUsingTreeScan = "";
    private String prefixForm = "";
    private String infixForm;
    private BinTree<String> tree;
    private Stack<BinTreeNode> stack;
    private BinTreeNode<String> newBranch;
    private String params;
    private String calculateRusult;
    private int numb1, numb2;

    public String getPostfixFormUsingStack() {
        return postfixFormUsingStack;
    }
    public String getPostfixFormUsingTreeScan() {
        return postfixFormUsingTreeScan;
    }
    public String getPrefixForm() { return prefixForm; }
    public String getCalculateRusult() { return calculateRusult; }

    public NotationHandler (String expr, String params) {
        infixForm = expr;
        this.params = params;
        makePostfixUsingStack(infixForm);
        makeTree();
        makePostfixUsingTreeScan(infixForm);
        makePrefix();
        calculate();
    }

    private void makeTree(){
        String template = new String(postfixFormUsingStack);
        stack = new Stack<BinTreeNode>();
        for (char symb: template.toCharArray()
             ) {
            if (!(symb == '+' || symb == '-' || symb == '*' || symb == '/' || symb == '^')){
                stack.push(new BinTreeNode(symb,null));
            }
            else {
                newBranch = new BinTreeNode(symb,null);
                newBranch.setRightNode(stack.pop());
                newBranch.setLeftNode(stack.pop());
                stack.push(newBranch);
            }
        }
        tree = new BinTree<String>();
        tree.setRoot(stack.pop());
    }

    void makePostfixUsingStack(String expr){
        Stack<String> stack = new Stack<String>();
        for (char symb: expr.toCharArray()
        ) {
            if (symb > 96 && symb < 123) {
                postfixFormUsingStack = postfixFormUsingStack.concat(String.valueOf(symb));
            }
            if (symb == '+' || symb == '-' || symb == '*' || symb == '/' || symb == '^') {
                while (!stack.empty() && priority(symb,stack.peek().charAt(0))) {
                    postfixFormUsingStack = postfixFormUsingStack.concat(String.valueOf(stack.pop()));
                }
                stack.push(String.valueOf(symb));
            }
            if (symb == '(') {
                stack.push(String.valueOf(symb));
            }
            if (symb == ')') {
                while (!stack.peek().equals("(")) {
                    postfixFormUsingStack = postfixFormUsingStack.concat(String.valueOf(stack.pop()));
                }
                stack.pop();
            }
        }
        while (!stack.empty()) {
            postfixFormUsingStack = postfixFormUsingStack.concat(String.valueOf(stack.pop()));
        }
    }

    boolean priority(char first, char second) {
        if (second != '+' && second != '-' && second != '*' && second != '/' && second != '^') {return false;}
        if (first == '+' || first == '-') {
            return true;
        }
        else if (first == '*' || first == '/') {
            if (second == '+' || second == '-') {
                return false;
            }
            else {
                return true;
            }
        }
        else {
            if (second == '+' || second == '-' || second == '*' || second == '/') {
                return false;
            }
            else {
                return true;
            }
        }
    }

    public void makePostfixUsingTreeScan(String string){
        movePostfix(tree.getRoot());
    }

    private void movePostfix(BinTreeNode<String> node) {
        if (node.getLeft()!=null) movePostfix(node.getLeft());
        if (node.getRight()!=null) movePostfix(node.getRight());
        postfixFormUsingTreeScan = postfixFormUsingTreeScan.concat(String.valueOf(node.getData()));
    }

    private void makePrefix(){
        movePrefix(tree.getRoot());
    }

    private void movePrefix(BinTreeNode<String> node){
        prefixForm = prefixForm.concat(String.valueOf(node.getData()));
        if (node.getLeft()!=null) movePrefix(node.getLeft());
        if (node.getRight()!=null) movePrefix(node.getRight());
    }

    private void calculate(){
        String template = new String(postfixFormUsingStack);
        Stack<String> tempStack = new Stack<String>();
        for (char symb: template.toCharArray()
        ) {
            if (!(symb == '+' || symb == '-' || symb == '*' || symb == '/' || symb == '^')){
                tempStack.push(String.valueOf(symb));
            }
            else {
                String operand1 = tempStack.pop();
                String help = new String();
                help = params.substring(params.indexOf(operand1) + 2,params.length());
                try {
                    numb1 = Integer.parseInt(params.substring(params.indexOf(operand1) + 2, params.indexOf(operand1) + 2 + help.indexOf("\n")));
                } catch (Exception e) {
                    numb1 = Integer.parseInt(operand1);
                }
                String operand2 = tempStack.pop();
                help = params.substring(params.indexOf(operand2) + 2,params.length());
                try {
                    numb2 = Integer.parseInt(params.substring(params.indexOf(operand2) + 2, params.indexOf(operand2) + 2 + help.indexOf("\n")));
                } catch (Exception e) {
                    numb2 = Integer.parseInt(operand2);
                }
                int newNumb = doCalculate(numb2,numb1,symb);
                tempStack.push(Integer.toString(newNumb));
            }
        }
        calculateRusult = tempStack.pop();
    }

    private int doCalculate(int fNumb, int sNumb, char op) {
        switch (op) {
            case '+': return fNumb+sNumb;
            case '-': return fNumb-sNumb;
            case '*': return fNumb*sNumb;
            case '/': return fNumb/sNumb;
            case '^': return (int)Math.pow(fNumb,sNumb);
            default: return 0;
        }
    }
}


