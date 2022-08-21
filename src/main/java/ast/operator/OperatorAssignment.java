package ast.operator;

import ast.Expression;
import ast.Identifier;
import ast.value.JSValue;
import org.js.Interpreter;

public class OperatorAssignment extends AbstractBinaryOperator {
    public OperatorAssignment(Expression lhs, Expression rhs) {
        super(lhs, rhs);
    }

    @Override
    public Object execute() {
        VERIFY(lhs instanceof Identifier);
        var dest = (Identifier) lhs;
        var src = (JSValue) rhs.execute();
        Interpreter.get().rewrite(dest, src);
        return src;
    }

    @Override
    public String getDump(int indent) {
        return " = ";
    }

    @Override
    public String toString() {
        return "=";
    }
}
