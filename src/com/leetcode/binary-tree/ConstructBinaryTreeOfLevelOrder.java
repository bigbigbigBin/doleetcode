import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ConstructBinaryTreeOfLevelOrder {


    // 使用的前提，必须是完全二叉树，即叶子节点都在最后一层，或者最后第二层，且都在左边
    public static <T> T constructTree(Integer[] array, Class<T> clazz) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        if (array.length == 0) {
            return null;
        }

        T[] originNodes = (T[])Array.newInstance(clazz, array.length);

        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                originNodes[i] = null;
            } else {
                T t = (T) Arrays.stream(clazz.getConstructors())
                        .filter(ct -> ct.getParameterTypes().length == 1 && ct.getParameterTypes()[0] == int.class)
                        .findFirst().get()
                        .newInstance( array[i].intValue());
                        originNodes[i] = t;
            }
        }

        for (int i = 0; i * 2 + 2 < array.length; i++) {
            if (array[i] != null) {
                originNodes[i].getClass().getDeclaredField("left").set(originNodes[i], originNodes[i*2+1]);
                originNodes[i].getClass().getDeclaredField("right").set(originNodes[i], originNodes[i*2+2]);
            }
        }
        return originNodes[0];
    }

    public static void main(String[] args) throws NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {
        SymmetricTree symmetricTree = new SymmetricTree();
        Integer[] array = {1, 2, null};
        SymmetricTree.TreeNode root = ConstructBinaryTreeOfLevelOrder.constructTree(array, SymmetricTree.TreeNode.class);
        System.out.println(root);
        System.out.println(root.left);
        System.out.println(root.right);

    }

}
