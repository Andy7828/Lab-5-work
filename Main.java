class Main {
  public static void main(String[] args) {

      // Step 1 - Create a BST tree object called lab5Tree
      BinarySearchTree lab5Tree = new BinarySearchTree();

      // Step 2 - Insert the following values: 13, 22, 36, 5, 48, 17, 39, 2, 26, 40, 29, 34, 10
      int[] valuesToInsert = {13, 22, 36, 5, 48, 17, 39, 2, 26, 40, 29, 34, 10};
      for (int value : valuesToInsert) {
          lab5Tree.insert(value);
      }

      // Step 3 - Delete the value 17
      lab5Tree.delete(17);

      // Step 4 - Traverse and output the values using inorder (sorted)
      System.out.println("Inorder traversal:");
      lab5Tree.inorder();

      // Step 5 - Traverse and output the values using postorder
      System.out.println("\nPostorder traversal:");
      lab5Tree.postorder();

      // Step 6 - Traverse and output the values using preorder
      System.out.println("\nPreorder traversal:");
      lab5Tree.preorder();

      // Step 7 - Display the result of a search for the value 36
      System.out.println("\nSearch for 36: " + (lab5Tree.search(36) ? "Found" : "Not Found"));

      // Step 8 - Display the result of a search for the value 37
      System.out.println("Search for 37: " + (lab5Tree.search(37) ? "Found" : "Not Found"));

      // Step 9 - Using the path() method, display the path from the root to 2
      System.out.println("Path to 2: " + lab5Tree.path(2));

      // Step 10 - Display the path from the root to 34
      System.out.println("Path to 34: " + lab5Tree.path(34));
  }
}

class BinarySearchTree {
  private Node root;

  private static class Node {
      int value;
      Node left, right;

      Node(int value) {
          this.value = value;
          left = right = null;
      }
  }

  public void insert(int value) {
      root = insertRec(root, value);
  }

  private Node insertRec(Node root, int value) {
      if (root == null) {
          return new Node(value);
      }
      if (value < root.value) {
          root.left = insertRec(root.left, value);
      } else if (value > root.value) {
          root.right = insertRec(root.right, value);
      }
      return root;
  }

  public void delete(int value) {
      root = deleteRec(root, value);
  }

  private Node deleteRec(Node root, int value) {
      if (root == null) return root;

      if (value < root.value) {
          root.left = deleteRec(root.left, value);
      } else if (value > root.value) {
          root.right = deleteRec(root.right, value);
      } else {
          if (root.left == null) return root.right;
          if (root.right == null) return root.left;

          root.value = minValue(root.right);
          root.right = deleteRec(root.right, root.value);
      }
      return root;
  }

  private int minValue(Node root) {
      int minValue = root.value;
      while (root.left != null) {
          root = root.left;
          minValue = root.value;
      }
      return minValue;
  }

  public boolean search(int value) {
      return searchRec(root, value);
  }

  private boolean searchRec(Node root, int value) {
      if (root == null) return false;
      if (root.value == value) return true;
      return value < root.value ? searchRec(root.left, value) : searchRec(root.right, value);
  }

  public String path(int value) {
      StringBuilder path = new StringBuilder();
      pathRec(root, value, path);
      return path.toString();
  }

  private boolean pathRec(Node root, int value, StringBuilder path) {
      if (root == null) return false;

      path.append(root.value).append(" ");
      if (root.value == value) return true;

      if (value < root.value) {
          if (pathRec(root.left, value, path)) return true;
      } else {
          if (pathRec(root.right, value, path)) return true;
      }

      path.setLength(path.length() - (root.value + " ").length());
      return false;
  }

  public void inorder() {
      inorderRec(root);
  }

  private void inorderRec(Node root) {
      if (root != null) {
          inorderRec(root.left);
          System.out.print(root.value + " ");
          inorderRec(root.right);
      }
  }

  public void postorder() {
      postorderRec(root);
  }

  private void postorderRec(Node root) {
      if (root != null) {
          postorderRec(root.left);
          postorderRec(root.right);
          System.out.print(root.value + " ");
      }
  }

  public void preorder() {
      preorderRec(root);
  }

  private void preorderRec(Node root) {
      if (root != null) {
          System.out.print(root.value + " ");
          preorderRec(root.left);
          preorderRec(root.right);
      }
  }
}