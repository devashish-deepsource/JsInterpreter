package org.js;

import ast.ASTNode;
import ast.Identifier;
import ast.Program;
import ast.Scope;
import myutils.Assertable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Interpreter implements Assertable {
    private static Interpreter interpreter = null;
    private List<Scope> stack = new ArrayList<>();

    private Interpreter() {
        Scope globalScope = new Scope();
        stack.add(globalScope);
    }

    public Interpreter enterScope(final Scope scope) {
        stack.add(scope);
        return this;
    }

    public Optional<ASTNode> queryScope(final Identifier identifier) {
        for (int i = stack.size() - 1; i >= 0; i--) {
            var scope = stack.get(i);
            var entityOrNull = scope.getIdentifierEntity(identifier);
            if (entityOrNull.isPresent())
                return entityOrNull;
        }
        return Optional.empty();
    }

    public Scope getCurrentScope () {
        Assert(stack.size() >= 1);
        return stack.get(stack.size() - 1);
    }

    public Interpreter exitCurrentScope() {
        stack.remove(stack.size() - 1);
        return this;
    }

    public Object run (final Program program) {
        return program.execute();
    }

    public static Interpreter get() {
        if (interpreter == null)
            interpreter = new Interpreter();
        return interpreter;
    }
}
