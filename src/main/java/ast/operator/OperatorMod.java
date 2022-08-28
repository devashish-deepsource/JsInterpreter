package ast.operator;

import ast.Expression;
import ast.value.JSValue;

import static myutils.Macro.verify;

public class OperatorMod extends AbstractBinaryOperator {
    public OperatorMod(Expression lhs, Expression rhs) {
        super(lhs, rhs);
    }

    @Override
    public Object execute() {
        var lhsValue = Interface.valueOf(lhs.execute());
        var rhsValue = Interface.valueOf(rhs.execute());
        return lhsValue.mod(rhsValue);
    }

    public static interface Interface extends JSValue {
        public Expression mod(Expression rhs);

        static Interface valueOf(Object value) {
            verify(value instanceof Interface);
            return (Interface) value;
        }
    }

    @Override
    public String toString() {
        return "%";
    }
}
