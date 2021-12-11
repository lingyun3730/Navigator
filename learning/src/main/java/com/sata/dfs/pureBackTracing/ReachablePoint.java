package com.sata.dfs.pureBackTracing;

/**
 * Lintcode 1036
 */
public class ReachablePoint {
    /**
     * dfs, 找到一条通路就行，所以有返回值，但是会超时。 stackoverflow.
     * @param sx
     * @param sy
     * @param tx
     * @param ty
     * @return
     */
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        // write your code here
        System.out.print("sx:" + sx + " " + "sy:" + sy);
        if(tx < sx || ty < sy) return false;
        if(sx == tx && sy == ty) return true;
        if(reachingPoints(sx + sy, sy, tx, ty)) return true;
        if(reachingPoints(sx, sx + sy, tx, ty)) return true;
        return false;
    }

    public boolean reachingPointsI(int sx, int sy, int tx, int ty) {
        if(sx < tx && sy < ty) {
            if(tx > ty) {
                tx = tx % ty;
            }else{
                ty = ty % tx;
            }
        }
        return (tx == sx && ty % sy == 0) || (ty == sy && tx % sx == 0);
    }
}
