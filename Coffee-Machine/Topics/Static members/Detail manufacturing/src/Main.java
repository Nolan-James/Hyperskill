class ManufacturingController {
    static int requestedProducts = 0;

    public static String requestProduct(String product) {
        requestedProducts++;
        return requestedProducts + ". Requested " + product;
    }

    public static int getNumberOfProducts() {
        return requestedProducts;
    }
}