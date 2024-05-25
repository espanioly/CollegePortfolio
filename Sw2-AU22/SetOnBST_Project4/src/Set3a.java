import java.util.Iterator;

import components.binarytree.BinaryTree;
import components.binarytree.BinaryTree1;
import components.set.Set;
import components.set.SetSecondary;

/**
 * {@code Set} represented as a {@code BinaryTree} (maintained as a binary
 * search tree) of elements with implementations of primary methods.
 *
 * @param <T>
 *            type of {@code Set} elements
 * @mathdefinitions <pre>
 * IS_BST(
 *   tree: binary tree of T
 *  ): boolean satisfies
 *  [tree satisfies the binary search tree properties as described in the
 *   slides with the ordering reported by compareTo for T, including that
 *   it has no duplicate labels]
 * </pre>
 * @convention IS_BST($this.tree)
 * @correspondence this = labels($this.tree)
 *
 * @author Aaron Heishman, Sam Espanioly
 *
 */
public class Set3a<T extends Comparable<T>> extends SetSecondary<T> {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Elements included in {@code this}.
     */
    private BinaryTree<T> tree;

    /**
     * Returns whether {@code x} is in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} to be searched
     * @param x
     *            the label to be searched for
     * @return true if t contains x, false otherwise
     * @requires IS_BST(t)
     * @ensures isInTree = (x is in labels(t))
     */
    private static <T extends Comparable<T>> boolean isInTree(BinaryTree<T> t,
            T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";
        boolean isInTree = false;
        if (t.size() > 0) {
            /*
             * Set up lhs (left hand side) and rhs (right hand side)
             */
            BinaryTree<T> lhs = t.newInstance();
            BinaryTree<T> rhs = t.newInstance();
            /*
             * Disassemble the tree @Code t
             */
            T root = t.disassemble(lhs, rhs);
            if (!isInTree) {
                if (x.compareTo(root) < 0) {
                    // Check left hand side for x
                    isInTree = isInTree(lhs, x);
                } else {
                    // Check right hand side for x
                    isInTree = isInTree(rhs, x);
                }
                /*
                 * Check if the root matches x
                 */
                if (root.equals(x)) {
                    isInTree = true;
                }
            }
            /*
             * Put the tree back together
             */
            t.assemble(root, lhs, rhs);
        }
        return isInTree;
    }

    /**
     * Inserts {@code x} in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} to be searched
     * @param x
     *            the label to be inserted
     * @aliases reference {@code x}
     * @updates t
     * @requires IS_BST(t) and x is not in labels(t)
     * @ensures IS_BST(t) and labels(t) = labels(#t) union {x}
     */
    private static <T extends Comparable<T>> void insertInTree(BinaryTree<T> t,
            T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";
        /*
         * Check if tree is empty and insert x into tree if it is, else check
         * where to insert x
         */
        if (t.size() < 1) {
            /*
             * Set up left hand side (lhs) and right hand side (rhs)
             */
            BinaryTree<T> lhs = t.newInstance();
            BinaryTree<T> rhs = t.newInstance();
            /*
             * Assemble tree with x as root - No need to dissassemble as the
             * tree is empty.
             */
            t.assemble(x, lhs, rhs);
        } else {
            /*
             * Set up left hand side (lhs) and right hand side {rhs)
             */
            BinaryTree<T> lhs = t.newInstance();
            BinaryTree<T> rhs = t.newInstance();
            /*
             * Disassemble the binary tree
             */
            T root = t.disassemble(lhs, rhs);
            if (x.compareTo(root) < 0) {
                // Make recursive call left if root is bigger than x
                insertInTree(lhs, x);
            } else {
                // Make recursive call right if root is smaller than x
                insertInTree(rhs, x);
            }
            /*
             * Put the tree back together
             */
            t.assemble(root, lhs, rhs);
        }
    }

    /**
     * Removes and returns the smallest (left-most) label in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} from which to remove the label
     * @return the smallest label in the given {@code BinaryTree}
     * @updates t
     * @requires IS_BST(t) and |t| > 0
     * @ensures <pre>
     * IS_BST(t)  and  removeSmallest = [the smallest label in #t]  and
     *  labels(t) = labels(#t) \ {removeSmallest}
     * </pre>
     */
    private static <T> T removeSmallest(BinaryTree<T> t) {
        assert t != null : "Violation of: t is not null";
        assert t.size() > 0 : "Violation of: |t| > 0";
        /*
         * Set up left hand side (lhs) and right hand side (rhs)
         */
        BinaryTree<T> lhs = t.newInstance();
        BinaryTree<T> rhs = t.newInstance();
        /*
         * Disassemble the tree {@code t} - This will be the smallest value
         */
        T root = t.disassemble(lhs, rhs);
        /*
         * if {@Code lhs} has more nodes then make recursive call, else if
         * {@Code lhs} is empty, but {@Code rhs} has nodes then transfer those
         * nodes to t
         */
        if (lhs.size() > 0) {
            /*
             * Save the oldRoot for re-assembly
             */
            T oldRoot = root;
            root = removeSmallest(lhs);
            t.assemble(oldRoot, lhs, rhs);
        } else if (rhs.size() > 0) {
            /*
             * transfer the tree in {@Code rhs} to root
             */
            t.transferFrom(rhs);
        }
        return root;
    }

    /**
     * Finds label {@code x} in {@code t}, removes it from {@code t}, and
     * returns it.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} from which to remove label {@code x}
     * @param x
     *            the label to be removed
     * @return the removed label
     * @updates t
     * @requires IS_BST(t) and x is in labels(t)
     * @ensures <pre>
     * IS_BST(t)  and  removeFromTree = x  and
     *  labels(t) = labels(#t) \ {x}
     * </pre>
     */
    private static <T extends Comparable<T>> T removeFromTree(BinaryTree<T> t,
            T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";
        assert t.size() > 0 : "Violation of: x is in labels(t)";
        /*
         * Set up left hand side (lhs) and right hand side (rhs)
         */
        BinaryTree<T> lhs = t.newInstance();
        BinaryTree<T> rhs = t.newInstance();
        /*
         * Disassemble to get root
         */
        T root = t.disassemble(lhs, rhs);
        /*
         * Check if the root is the element being removed
         */
        if (!root.equals(x)) {
            /*
             * Variable to hold original root since root is being returned
             */
            T oldRoot = root;
            if (x.compareTo(root) < 0) {
                // Make  recursive call to the left
                root = removeFromTree(lhs, x);
                t.assemble(oldRoot, lhs, rhs);
            } else {
                //Make recursive call to the right
                root = removeFromTree(rhs, x);
                t.assemble(oldRoot, lhs, rhs);
            }
        } else {
            /*
             * Root is the element being removed
             */
            if (rhs.size() > 0) {
                /*
                 * Make the smallest in {@Code rhs} the new root of {@Code t}
                 */
                T smallestNodeRHS = removeSmallest(rhs);
                t.assemble(smallestNodeRHS, lhs, rhs);
            } else if (lhs.size() > 0) {
                /*
                 * Transfer the elements from the left side to {@Code t} because
                 * the rhs is empty.
                 */
                t.transferFrom(lhs);
            }
        }
        return root;
    }

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.tree = new BinaryTree1<T>();
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Set3a() {
        this.createNewRep();
    }

    /*
     * Standard methods -------------------------------------------------------
     */

    @SuppressWarnings("unchecked")
    @Override
    public final Set<T> newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(Set<T> source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof Set3a<?> : ""
                + "Violation of: source is of dynamic type Set3<?>";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case: source must be of dynamic type Set3a<?>, and
         * the ? must be T or the call would not have compiled.
         */
        Set3a<T> localSource = (Set3a<T>) source;
        this.tree = localSource.tree;
        localSource.createNewRep();
    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    @Override
    public final void add(T x) {
        assert x != null : "Violation of: x is not null";
        assert !this.contains(x) : "Violation of: x is not in this";
        insertInTree(this.tree, x);
    }

    @Override
    public final T remove(T x) {
        assert x != null : "Violation of: x is not null";
        assert this.contains(x) : "Violation of: x is in this";
        return removeFromTree(this.tree, x);
    }

    @Override
    public final T removeAny() {
        assert this.size() > 0 : "Violation of: this /= empty_set";
        return removeSmallest(this.tree);
    }

    @Override
    public final boolean contains(T x) {
        assert x != null : "Violation of: x is not null";
        return isInTree(this.tree, x);
    }

    @Override
    public final int size() {
        return this.tree.size();
    }

    @Override
    public final Iterator<T> iterator() {
        return this.tree.iterator();
    }

}
