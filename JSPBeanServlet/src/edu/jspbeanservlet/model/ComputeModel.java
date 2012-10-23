package edu.jspbeanservlet.model;

import java.math.BigDecimal;

/**
 * Catalin Dumitru
 * Universitatea Alexandru Ioan Cuza
 */
public class ComputeModel {
    public static enum Operation {
        ADD("+") {
            @Override
            public BigDecimal getResult(BigDecimal first, BigDecimal second) {
                return first.add(second);
            }
        },
        SUB("-") {
            @Override
            public BigDecimal getResult(BigDecimal first, BigDecimal second) {
                return first.subtract(second);
            }
        },
        DIV("/") {
            @Override
            public BigDecimal getResult(BigDecimal first, BigDecimal second) {
                return first.divide(second);
            }
        },
        MUL("*") {
            @Override
            public BigDecimal getResult(BigDecimal first, BigDecimal second) {
                return first.multiply(second);
            }
        };

        private String displayName;

        private Operation(String displayName) {
            this.displayName = displayName;
        }


        public String getDisplayName() {
            return displayName;
        }

        public abstract BigDecimal getResult(BigDecimal first, BigDecimal second);
    }

    private BigDecimal firstNumber = BigDecimal.ZERO;
    private BigDecimal secondNumber = BigDecimal.ZERO;
    private Operation operation = null;

    public String getFirstNumber() {
        return firstNumber.toPlainString();
    }

    public void setFirstNumber(String firstNumber) {
        this.firstNumber = new BigDecimal(firstNumber);
    }

    public String getSecondNumber() {
        return secondNumber.toPlainString();
    }

    public void setSecondNumber(String secondNumber) {
        this.secondNumber = new BigDecimal(secondNumber);
    }

    public String getOperation() {
        return operation.name();
    }

    public void setOperation(String operation) {
        this.operation = Operation.valueOf(operation);
    }

    public String getResult() {
        return operation.getResult(firstNumber, secondNumber).toPlainString();
    }

    public boolean isDefined() {
        return operation != null;
    }
}
