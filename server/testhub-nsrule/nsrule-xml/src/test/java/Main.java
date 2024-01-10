import org.dromara.testhub.nsrule.core.executer.mode.base.formula.FormulaNode;
import org.dromara.testhub.nsrule.core.expand.FormulaBuilder;
import org.dromara.testhub.nsrule.core.expand.impl.DefaultFormulaBuilder;

public class Main {
    public static void main(String[] args) {
        FormulaBuilder formulaBuilder = new DefaultFormulaBuilder();
//        FormulaNode node1 = formulaBuilder.getFormulaNode("${user}");
//        FormulaNode node2 = formulaBuilder.getFormulaNode("${user.books}");
//        FormulaNode node3 = formulaBuilder.getFormulaNode("${user.books.id}");
//        FormulaNode node4 = formulaBuilder.getFormulaNode("${user.books[-1].id}");
//        FormulaNode node5 = formulaBuilder.getFormulaNode("${user.books[${index}].id}");
//        FormulaNode node6 = formulaBuilder.getFormulaNode("${user.books[%{add()}].id}");
//        FormulaNode node7 = formulaBuilder.getFormulaNode("${user.books[*].id}");
//        FormulaNode node8 = formulaBuilder.getFormulaNode("${user.books[${index}:0].id}");
//        FormulaNode node9 = formulaBuilder.getFormulaNode("${user.books[${index}:%{add()}].id}");
//        FormulaNode node10 = formulaBuilder.getFormulaNode("${user.books[${index}:%{add()}:2].id}");
//        FormulaNode node11 = formulaBuilder.getFormulaNode("${books[?(id)]}");
//        FormulaNode node12 = formulaBuilder.getFormulaNode("${books[id='123']}");
//        FormulaNode node13 = formulaBuilder.getFormulaNode("${books[id like '123%']}");
//        FormulaNode node14 = formulaBuilder.getFormulaNode("${books[id not rlike '123%']}");
//        FormulaNode node15 = formulaBuilder.getFormulaNode("${books[id > ${index}]}");


//        FormulaNode node20 = formulaBuilder.getFormulaNode("%{add()}");
//        FormulaNode node21 = formulaBuilder.getFormulaNode("%{add(attr1:1)}");
//        FormulaNode node22 = formulaBuilder.getFormulaNode("%{add(attr1:${index})}");
//        FormulaNode node23 = formulaBuilder.getFormulaNode("%{add(attr1:%{add(attr1:${index})})}");
//        FormulaNode node24 = formulaBuilder.getFormulaNode("%{add(1)}");
//        FormulaNode node25 = formulaBuilder.getFormulaNode("%{add(1,2)}");
//        FormulaNode node26 = formulaBuilder.getFormulaNode("%{add(${index},${index})}");
//        FormulaNode node27 = formulaBuilder.getFormulaNode("%{add(attr1:${index},attr2:${index})}");
//        FormulaNode node28 = formulaBuilder.getFormulaNode("%{add(1,2).name}");


//        FormulaNode node40 = formulaBuilder.getFormulaNode("asd");
//        FormulaNode node41 = formulaBuilder.getFormulaNode("1");
//        FormulaNode node42 = formulaBuilder.getFormulaNode("-1");
//        FormulaNode node43 = formulaBuilder.getFormulaNode("1.0086");
//        FormulaNode node44 = formulaBuilder.getFormulaNode("-1.0086");
        FormulaNode node45 = formulaBuilder.getFormulaNode("{attr:{a:1},attr1:1}");
        FormulaNode node46 = formulaBuilder.getFormulaNode("{attr:2,attr1:1}");


//        FormulaNode node50 = formulaBuilder.getFormulaNode("1+2+3+%{add()}+(-1+2)*3/${P}");



//        FormulaNode node60 = formulaBuilder.getFormulaNode("asd${user}123");
//        FormulaNode node61 = formulaBuilder.getFormulaNode("(time)'2023-09-12'");

        System.out.println("---");
    }
}
