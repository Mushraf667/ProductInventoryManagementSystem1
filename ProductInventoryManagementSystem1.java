import java.util.*;
public class ProductInventoryManagementSystem1 {
    public static void main(String[] args) {
        HashSet<String> categories = new HashSet<>();
        TreeSet<String> sortedCategories = new TreeSet<>();
        LinkedHashSet<String> products = new LinkedHashSet<>();
        HashMap<Integer, Integer> inventory = new HashMap<>();
        HashMap<Integer, String> names = new HashMap<>();
        HashMap<Integer, String> category = new HashMap<>();
        HashMap<Integer, Double> prices = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("===== PRODUCT INVENTORY SYSTEM =====");
            System.out.println("1Add Product");
            System.out.println("2Display All Products");
            System.out.println("3Search Product");
            System.out.println("4Update Product Quantity");
            System.out.println("5Sell Product");
            System.out.println("6Remove Product");
            System.out.println("7Add Product Category");
            System.out.println("8Display Unique Categories");
            System.out.println("9Display Sorted Categories");
            System.out.println("10Display Recently Added Products");
            System.out.println("11Generate Inventory Report");
            System.out.println("12Exit");
            System.out.print("Enter Your Choice:");
            choice=sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter Product ID:");
                    int id=sc.nextInt();
                    sc.nextLine();
                    if (inventory.containsKey(id)) {
                        System.out.println("Product ID Already Exists");
                        break;
                    }
                    System.out.print("Enter Product Name: ");
                    String name=sc.nextLine();
                    System.out.print("Enter Category:");
                    String cat=sc.nextLine();
                    System.out.print("Enter Price:");
                    double price=sc.nextDouble();
                    System.out.print("Enter Quantity:");
                    int qty=sc.nextInt();
                    sc.nextLine();
                    if(price<0||qty<0) {
                        System.out.println("Invalid Price or Quantity");
                        break;
                    }
                    inventory.put(id,qty);
                    names.put(id,name);
                    category.put(id,cat);
                    prices.put(id,price);
                    categories.add(cat);
                    products.add(name);
                    System.out.println("Product Added Successfully");
                    break;
                case 2:
                    if (inventory.isEmpty()) {
                        System.out.println("No Products Available");
                    } else {
                        System.out.println("ID Product Category Price Quantity");
                        for (int key:inventory.keySet()) {
                            System.out.println(key+"\t"+names.get(key)+"\t"+category.get(key)+"\t"+prices.get(key)+"\t"+inventory.get(key));
                        }
                        System.out.println("Total Products:"+inventory.size());
                    }
                    break;
                case 3:
                    System.out.print("Enter Product ID:");
                    id=sc.nextInt();
                    if (inventory.containsKey(id)) {
                        System.out.println("Product Name:"+names.get(id));
                        System.out.println("Category:"+category.get(id));
                        System.out.println("Price:"+prices.get(id));
                        System.out.println("Quantity:"+inventory.get(id));
                    } else {
                        System.out.println("Product Not Found");
                    }
                    break;
                case 4:
                    System.out.print("Enter Product ID:");
                    id=sc.nextInt();
                    if (inventory.containsKey(id)) {
                        System.out.print("Enter New Quantity:");
                        qty=sc.nextInt();
                        if (qty>=0) {
                            inventory.put(id, qty);
                            System.out.println("Quantity Updated Successfully");
                        } else {
                            System.out.println("Invalid Quantity");
                        }
                    } else {
                        System.out.println("Product Not Found");
                    }
                    break;
                case 5:
                    System.out.print("Enter Product ID:");
                    id = sc.nextInt();
                    if (inventory.containsKey(id)) {
                        System.out.print("Enter Quantity Sold:");
                        qty=sc.nextInt();
                        if (qty>0&&qty<=inventory.get(id)) {
                            inventory.put(id,inventory.get(id)-qty);
                            System.out.println("Sale Completed Successfully");
                            System.out.println("Product:"+names.get(id));
                            System.out.println("Remaining Quantity:"+inventory.get(id));
                        } 
                        else {
                            System.out.println("Insufficient Stock or Invalid Quantity");
                        }
                    } else {
                        System.out.println("Product Not Found");
                    }
                    break;
                case 6:
                    System.out.print("Enter Product ID: ");
                    id = sc.nextInt();
                    if (inventory.containsKey(id)) {
                        String removedName = names.get(id);
                        String removedCategory = category.get(id);
                        inventory.remove(id);
                        names.remove(id);
                        prices.remove(id);
                        category.remove(id);
                        products.remove(removedName);
                        if (!category.containsValue(removedCategory)) {
                            categories.remove(removedCategory);
                        }
                        System.out.println("Product Removed Successfully");
                    } else {
                        System.out.println("Product Not Found");
                    }
                    break;
                case 7:
                    System.out.print("Enter Category:");
                    cat = sc.nextLine();
                    if (categories.add(cat)) {
                        System.out.println("Category Added Successfully!");
                    } else {
                        System.out.println("Category Already Exists!");
                    }
                    break;
                case 8:
                    System.out.println("Product Categories: " + categories);
                    System.out.println("Total Unique Categories: " + categories.size());
                    break;
                case 9:
                    sortedCategories.clear();
                    sortedCategories.addAll(categories);
                    System.out.println("Sorted Categories:"+sortedCategories);
                    System.out.println("First Category:"+(sortedCategories.isEmpty() ? "None" : sortedCategories.first()));
                    System.out.println("Last Category:"+(sortedCategories.isEmpty() ? "None" : sortedCategories.last()));
                    System.out.println("Descending Order:"+sortedCategories.descendingSet());
                    break;
                case 10:
                    System.out.println("Recently Added Products:"+products);
                    System.out.print("Enter Product Name to Search:");
                    name=sc.nextLine();
                    if(products.contains(name)){
                        System.out.println("Product Found");
                    } 
                    else {
                        System.out.println("Product Not Found");
                    }
                    break;
                case 11:
                    if(inventory.isEmpty()){
                        System.out.println("No Products Available");
                        break;
                    }
                    int total=0,highest=-1,low=0;
                    for(int q:inventory.values()) {
                        total+=q;
                        if(q>highest) {
                            highest=q;
                        }
                        if(q<5) 
                        {
                            low++;
                        }
                    }
                    System.out.println("=====INVENTORY REPORT=====");
                    System.out.println("Total Products:"+inventory.size());
                    System.out.println("Total Available Units:"+total);
                    System.out.println("Highest Available Quantity:"+highest);
                    System.out.println("Products Below 5 Quantity:"+low);
                    System.out.println("Unique Categories:"+categories);
                    System.out.println("Total Categories:"+categories.size());
                    break;
                case 12:
                    System.out.println("Exited");
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        } 
        while(choice!=12);
        sc.close();
    }
}