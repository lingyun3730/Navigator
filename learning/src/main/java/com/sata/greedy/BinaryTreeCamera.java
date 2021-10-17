package com.sata.greedy;

import com.sata.tree.TreeNode;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * LC 968
 */
public class BinaryTreeCamera {

    public int minCameraCover(TreeNode root) {
        //后序遍历 + 贪心，分情况思考。
        //贪心策略 ：为了尽可能少地放照相机，那么叶子结点就不要放照相机，我们定义三种状态： 0表示无覆盖，1表示照相机，2表示被覆盖。
        //当left == 0 || right == 0 : cur = 1, 父节点需要放一个照相机去覆盖左右子节点
        //当left == 1 || right == 1 : cur = 2，当前节点作为父节点被覆盖了
        //当left == 2 && right == 2 : cur = 0, 当左右子节点都被覆盖了，当前节点先不放照相机，等它的父节点放
        AtomicInteger res = new AtomicInteger(0);
        int rootStatus = postTraversal(root, res);
        if(rootStatus == 0) { //有可能递归到root节点发现root节点没有被覆盖，所以还得增加一个照相机。
            res.getAndIncrement();
        }
        return res.get();
    }

    private int postTraversal(TreeNode root, AtomicInteger cnt) {
        if(root == null) return 2; //null节点被初始化为被覆盖
        int left = postTraversal(root.left, cnt);
        int right = postTraversal(root.right, cnt);
        /**
         *只要一个子节点没有被覆盖，父节点就得增加一个照相机
         *left == 0 && right == 0
         *left == 0 && right == 1
         *left == 0 && right == 2
         *left == 1 && right == 0
         *left == 2 && right == 0
         */
        if(left == 0 || right == 0) {
            cnt.getAndIncrement();
            return 1;
        }else if(left == 1 || right == 1) {
            //left == 1 && right == 2
            //left == 2 && right == 1
            return 2;
        }else{
            //当左右子节点都被覆盖了，当前节点先不放照相机，等它的父节点放
            return 0; //left == 2 && right == 2
        }
    }
}
