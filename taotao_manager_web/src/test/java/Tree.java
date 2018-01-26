/**
 * class_name: Tree
 * package:
 * describe: TODO
 *
 * @author: Liuxianglong
 * @date: 2018/1/24
 * creat_time: 14:53
 **/


public class Tree {

    private static String tree = "tree";


    String getTree() {
        return tree;
    }


    public static class Elm extends Tree {
        private static String tree = "elm";

        public static void main(String[] args) {
            new Elm().go(new Tree());
        }

        void go(Tree t) {
            String s = t.getTree() + Elm.tree + tree + (new Elm().getTree());
            System.out.println(s);
        }
    }



}
