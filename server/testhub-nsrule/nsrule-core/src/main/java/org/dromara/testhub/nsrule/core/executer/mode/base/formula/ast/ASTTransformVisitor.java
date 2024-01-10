package org.dromara.testhub.nsrule.core.executer.mode.base.formula.ast;

import org.dromara.testhub.nsrule.core.constant.RuleConstant;
import org.dromara.testhub.nsrule.core.executer.mode.base.formula.*;
import org.dromara.testhub.nsrule.core.executer.mode.base.formula.antlr.FormulaBaseVisitor;
import org.dromara.testhub.nsrule.core.executer.mode.base.formula.antlr.FormulaParser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ASTTransformVisitor extends FormulaBaseVisitor<FormulaNode> {

    @Override
    public FormulaNode visitFormula(FormulaParser.FormulaContext ctx) {
        FormulaNode root = null;
        String dataType = null;
        if(ctx.dataType != null){
            dataType = ctx.dataType.getText();
        }

        if (ctx.funcNode() != null) {
            root = visitFuncNode(ctx.funcNode());
        } else if (ctx.pathNode() != null) {
            root = visitPathNode(ctx.pathNode());
        } else if (ctx.arithmetic() != null) {
            root = visitArithmetic(ctx.arithmetic());
        } else if (ctx.dataNode() != null) {
            root = visitDataNode(ctx.dataNode());
        }
        if (!ctx.formula().isEmpty()) {
            List<FormulaNode> datas = new ArrayList<>();
            datas.add(root);
            for (int index = 0; index < ctx.formula().get(0).getChildCount(); index++) {
                datas.add(visit(ctx.formula().get(0).getChild(index)));
            }
            String text = ctx.getText();

            root = new MixNode(datas,text);
        }
        assert root != null;
        root.setDataType(dataType);

        return root;
    }

    @Override
    public FormulaNode visitPathNode(FormulaParser.PathNodeContext ctx) {
        FormulaNode pathNode = getJsonpath(ctx.jsonpath());
        pathNode.text = ctx.getText();
        return pathNode;
    }

    public FormulaNode getJsonpath(FormulaParser.JsonpathContext ctx) {
        PathNode pathNode = new PathNode(ctx.getText());
        List<PathNode.PathItem> items = new ArrayList<>();
        items.add(new PathNode.PathItem(ctx.IDENTIFIER().getText()));
        for (FormulaParser.SubscriptContext subscriptContext : ctx.subscript()) {
            PathNode.PathItem item = null;
            if (subscriptContext.arraySubscript() != null) {
                if (subscriptContext.arraySubscript().arrayIndex() != null) {
                    FormulaParser.ArrayIndexContext arrayIndex = subscriptContext.arraySubscript().arrayIndex();
                    if (arrayIndex.listIndex().isEmpty()) {
                        item = new PathNode.PathItem(getNumIndex(arrayIndex.numIndex()));
                    } else {
                        List<FormulaNode> nodes = new ArrayList<>();
                        nodes.add(getNumIndex(arrayIndex.numIndex()));
                        for (FormulaParser.ListIndexContext temp : arrayIndex.listIndex()) {
                            nodes.add(getNumIndex(temp.numIndex()));
                        }
                        item = new PathNode.PathItem(nodes);
                    }
                } else if (subscriptContext.arraySubscript().interval() != null) {
                    item = getInterval(subscriptContext.arraySubscript().interval());
                } else if (subscriptContext.arraySubscript().condition() != null) {
                    item = getCondition(subscriptContext.arraySubscript().condition());
                } else if (subscriptContext.arraySubscript().all() != null) {
                    item = new PathNode.PathItem(PathNode.PathItem.TYPE_ALL);
                }
            } else {
                item = new PathNode.PathItem(subscriptContext.dotIdentifier().IDENTIFIER().getText());
            }
            items.add(item);
        }
        pathNode.setItems(items);
        return pathNode;
    }

    private PathNode.PathItem getCondition(FormulaParser.ConditionContext ctx) {
        PathNode.PathItem item = null;
        if (ctx.notNull() != null) {
            item = new PathNode.PathItem(ctx.notNull().IDENTIFIER().getText(), true);
        } else if (ctx.expression() != null) {
            FormulaParser.ExpressionContext expression = ctx.expression();
            String attrName = expression.IDENTIFIER().getText();
            if (expression.op() != null) {
                item = new PathNode.PathItem(attrName,expression.op().getText(),visitFormula(expression.data));
            } else if (expression.strOp() != null) {
                item = new PathNode.PathItem(attrName,expression.strOp().getText(),visitFormula(expression.data));
            } else {
                String op = null;
                if (expression.NOT() != null) {
                    op = expression.NOT().getText() + " " + expression.IN().getText();
                } else {
                    op = expression.IN().getText();
                }
                List<FormulaNode> datas = new ArrayList<>();
                for (FormulaParser.FormulaContext formulaContext : expression.formula()) {
                    datas.add(visitFormula(formulaContext));
                }
                item = new PathNode.PathItem(attrName,op,visitFormula(expression.data));
            }
        }
        return item;
    }

    private PathNode.PathItem getInterval(FormulaParser.IntervalContext ctx) {
        PathNode.PathItem item = null;
        if (ctx.arrayInterval() != null) {
            FormulaParser.ArrayIntervalContext arrayInterval = ctx.arrayInterval();
            if (arrayInterval.step != null) {
                item = new PathNode.PathItem(getNumIndex(arrayInterval.start), getNumIndex(arrayInterval.end), getNumIndex(arrayInterval.step));
            } else {
                item = new PathNode.PathItem(getNumIndex(arrayInterval.start), getNumIndex(arrayInterval.end));
            }
        } else if (ctx.firstInterval() != null) {
            item = new PathNode.PathItem(getNumIndex(ctx.firstInterval().numIndex()), null);
        } else if (ctx.toInterval() != null) {
            item = new PathNode.PathItem(null, getNumIndex(ctx.toInterval().numIndex()));
        }
        return item;
    }

    private FormulaNode getNumIndex(FormulaParser.NumIndexContext ctx) {
        if (ctx.INT() != null) {
            return new DataNode(ctx.getText(), Long.parseLong(ctx.getText()));
        } else if (ctx.funcNode() != null) {
            return visitFuncNode(ctx.funcNode());
        } else if (ctx.pathNode() != null) {
            return visitPathNode(ctx.pathNode());
        }
        return new DataNode(ctx.getText(), ctx.getText());
    }

    @Override
    public FormulaNode visitDataNode(FormulaParser.DataNodeContext ctx) {
        if (ctx.decimal() != null) {
            return new DataNode(ctx.decimal().getText(), new BigDecimal(ctx.decimal().getText()));
        } else if (ctx.SingleQuoteAnyText() != null) {
            return new DataNode(ctx.SingleQuoteAnyText().getText());
        } else if (ctx.IDENTIFIER() != null) {
            return new DataNode(ctx.IDENTIFIER().getText());
        }else if (ctx.LBRACKET()!=null){
            if(ctx.dataNode().isEmpty()){
                List<FormulaNode> datas = new ArrayList<>();
                for(FormulaParser.FormulaContext subCtx:ctx.formula()){
                    datas.add(visitFormula(subCtx));
                }
                return new DataNode(ctx.getText(),datas);
            }else {
                List<Object> datas = new ArrayList<>();
                for(FormulaParser.DataNodeContext subCtx:ctx.dataNode()){
                    datas.add(getDataNode(subCtx));
                }
                return new DataNode(ctx.getText(),datas);
            }
        }else if (ctx.LCURLY()!=null){
            if(ctx.keyVal().isEmpty()){
                Map<String, FormulaNode> map = new HashMap<>();
                for (FormulaParser.KeyFormulaContext subCtx : ctx.keyFormula()) {
                    map.put(subCtx.IDENTIFIER().getText(), visitFormula(subCtx.formula()));
                }
                return new DataNode(ctx.getText(), map);
            }else {
                Map<String, Object> data = new HashMap<>();
                for (FormulaParser.KeyValContext subCtx : ctx.keyVal()) {
                    data.put(subCtx.key.getText(),  getData(subCtx));
                }
                return new DataNode(ctx.getText(), data);
            }
        }
        return null;
    }
    private Object getData(FormulaParser.KeyValContext ctx) {
        if (ctx.decimal() != null) {
            return new BigDecimal(ctx.decimal().getText());
        } else if (ctx.SingleQuoteAnyText() != null) {
            return ctx.SingleQuoteAnyText().getText();
        } else if (ctx.val != null) {
            return ctx.val.getText();
        }
        return null;
    }

    private Object getDataNode(FormulaParser.DataNodeContext ctx) {
        if (ctx.decimal() != null) {
            return new BigDecimal(ctx.decimal().getText());
        } else if (ctx.SingleQuoteAnyText() != null) {
            return ctx.SingleQuoteAnyText().getText();
        } else if (ctx.IDENTIFIER() != null) {
            return ctx.IDENTIFIER().getText();
        }
        return null;
    }

    @Override
    public FormulaNode visitArithmetic(FormulaParser.ArithmeticContext ctx) {
        if (ctx.factor() != null) {
            return visitFactor(ctx.factor());
        }
        return getArithmetic(ctx.getText(),ctx.left, ctx.right, ctx.muldiv(), ctx.addsub());
    }


    @Override
    public FormulaNode visitBinArithmetic(FormulaParser.BinArithmeticContext ctx) {
        return getArithmetic(ctx.getText(),ctx.left, ctx.right, ctx.muldiv(), ctx.addsub());
    }
    private FormulaNode getArithmetic(
            String text,
            FormulaParser.ArithmeticContext left,
            FormulaParser.ArithmeticContext right,
            FormulaParser.MuldivContext muldiv,
            FormulaParser.AddsubContext addsub) {

        ArithmeticNode arithmeticNode = new ArithmeticNode(text);
        arithmeticNode.setLeft(visitArithmetic(left));
        arithmeticNode.setRight(visitArithmetic(right));

        if (muldiv != null) {
            if (muldiv.MUL() != null) {
                arithmeticNode.setOp(RuleConstant.ArithmeticType.MUL);
            } else if (muldiv.DIV() != null) {
                arithmeticNode.setOp(RuleConstant.ArithmeticType.DIV);
            } else {
                arithmeticNode.setOp(RuleConstant.ArithmeticType.REM);
            }
        } else {
            arithmeticNode.setOp(addsub.ADD() != null ? RuleConstant.ArithmeticType.ADD : RuleConstant.ArithmeticType.SUB);
        }

        return arithmeticNode;
    }

    @Override
    public FormulaNode visitFactor(FormulaParser.FactorContext ctx) {
        if (ctx.decimal() != null) {
            return new DataNode(ctx.decimal().getText(),new BigDecimal(ctx.decimal().getText()));
        } else if (ctx.binArithmetic() != null) {
            return visitBinArithmetic(ctx.binArithmetic());
        } else if (ctx.pathNode() != null) {
            return visitPathNode(ctx.pathNode());
        } else if (ctx.funcNode() != null) {
            return visitFuncNode(ctx.funcNode());
        }
        return null;
    }

    @Override
    public FormulaNode visitFuncNode(FormulaParser.FuncNodeContext ctx) {

        FuncNode funcNode = new FuncNode(ctx.getText());

        funcNode.func = ctx.IDENTIFIER().getSymbol().getText();

        List<FuncNode.Param> params = new ArrayList<>();
        int index = 0;
        for (FormulaParser.ParamContext paramContext : ctx.params().param()) {
            params.add(getParam(paramContext, index));
            index++;
        }
        funcNode.params = params;
        if (ctx.jsonpath() != null) {
            funcNode.lastAttr = (PathNode) getJsonpath(ctx.jsonpath());
        }

        return funcNode;
    }


    private FuncNode.Param getParam(FormulaParser.ParamContext ctx, int index) {
        FuncNode.Param param = new FuncNode.Param();

        //key val 形式
        if (ctx.IDENTIFIER() != null) {
            param.setCode(ctx.IDENTIFIER().getText());
            param.setNode(visitFormula(ctx.formula()));
        } else {
            param.setCode(index + "");
            param.setNode(visitFormula(ctx.formula()));
        }

        return param;
    }

}
