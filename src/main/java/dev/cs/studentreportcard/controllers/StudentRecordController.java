package dev.cs.studentreportcard.controllers;

import dev.cs.studentreportcard.DTO.StudentRecordHeader;
import dev.cs.studentreportcard.models.StudentRecord;
import dev.cs.studentreportcard.repositories.StudentRepository;
import dev.cs.studentreportcard.services.StudentRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/records")
public class StudentRecordController {

    @Autowired
    private StudentRecordService studentRecordService;
    @Autowired
    private StudentRepository studentRepository;

    public StudentRecordController(StudentRecordService studentRecordService, StudentRepository studentRepository) {

        this.studentRecordService = studentRecordService;
        this.studentRepository = studentRepository;
    }

    public StudentRecordController() {
    }


      @GetMapping("/search/test")
    public ResponseEntity<List<StudentRecordHeader>> showAllReports() {
        System.out.println("/Testing: /records/search/test");
        var x = studentRecordService.getStudentRecordReport();
          System.out.println("x:" + x);
        return new ResponseEntity<>(x, HttpStatus.OK);
    }


    /*this page should have a search button and a search text
     * wheen user hits search button it should display the result
     * in another method /search/{studentId}*/
    /*
    @GetMapping("/search")
    public String searchRecords() {
        return "rsearch";
    }
    */
    /*    *//*TODO - Postman test passed localhost:8081/records/search/4 */
   /* @GetMapping("/search/{studentid}")
    public ResponseEntity<List<StudentRecord>> searchRecords(@PathVariable("studentid") Integer studentId, Model model) {
        System.out.println("/Testing: /records/search/studentdIdn is hit");
        Optional<Student> studentids = studentRepository.findAll().stream().filter(x -> x.getStudentId() == studentId).findFirst();
        System.out.println(studentids.get().getStudentId());
        // var x = studentRecordService.searchRecordsByStudentId(studentids.orElseGet());
        // System.out.println("Testing results : " + x);
        // model.addAttribute("records", x);
        // return new ResponseEntity<>(x, HttpStatus.OK);
    }*/

    /* TODO - Testeed for webpage */

    /*
    @GetMapping("/search/{studentid}")
    public String searchRecords(@PathVariable("studentid") Integer studentId, Model model) {
        System.out.println("/TBDeleted - Testing: /records/search/studendIdn is hit");
        model.addAttribute("records", studentRecordService.searchRecordsByStudentId(studentId));
       return "rsearchresult";
    }*/
    /*
    // Postmat
    @GetMapping("/stat")
    public List<StudentRecordHeader> getStatistics() {
        return studentRecordService.getStudentStatisticsWithRank();
    }


    /*
    @GetMapping("index")
    public String showAllRecords(HttpServletRequest request, Model model) {
       int page = 0;
        int size = 5;
        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }
        model.addAttribute("records", studentRecordService.listAllRecords(PageRequest.of(page,size)));

        System.out.println("Test records");
       return "recordslist";
       // return "index";
    }*/

/*
    @GetMapping("/page")
    public String showAllProductByPage(HttpServletRequest request, @RequestParam("page") int page, Model model) {
        int size = 5;
        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }
        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }
        model.addAttribute("products", productService.listAllProducts(PageRequest.of(page, size)));
        return "productlist";
    }

    // dispaly products by page number
    @GetMapping("/detail/{productcode}")
    public String showProductDetail(@PathVariable String productcode, Model model) {
        model.addAttribute("product", productService.getProductByProductCode(productcode));
        return "productdetail";
    }
    */
/*

    @GetMapping("/cart/{productcode}")
    public void addItemToCart(@PathVariable String productcode, HttpServletResponse response) throws IOException {
        productService.addItemToVirtualCart(productcode);
        productService.decreaseStockQuantity(productcode);
        response.sendRedirect("/product/");
    }
    */
/****
 * display cart items selected by the user
 * @param model
 * @return
 *//*

    // customers can see what they have in their cart
    @GetMapping("/mycart")
    public String showItemInCart(Model model) {
        List<ProductVirtualCartDTO> obj = productService.listAllCartItems();
        if (obj != null) {
            model.addAttribute("products", productService.listAllCartItems());
        }
        model.addAttribute("total", productService.totalCharges());
        return "productcart";

    }
    // customers can see what they have in their cart
    @GetMapping("/emptycart")
    public void cleanVirtualCart(HttpServletResponse response) throws IOException {
        productService.clearVirtualCart();
        response.sendRedirect("/product/mycart");
    }
    // customers car remove products from cart
    @GetMapping("/removeproduct/{productcode}")
    public void removeItemfromCart(HttpServletResponse response, @PathVariable String productcode) throws IOException {
        productService.removeItemFromVirtualCart(productcode);
        response.sendRedirect("/product/mycart");
    }
    @GetMapping("/admin")
    public String productDashboard(HttpServletRequest request, Model model) {


        int page = 0;
        int size = 5;
        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }
        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }
        model.addAttribute("products", productService.listAllProducts(PageRequest.of(page, size)));
        return "adminproduct";
    }
    @GetMapping("/admin/page")
    public String productDashboardByPage(HttpServletRequest request, @RequestParam("page") int page, Model model) {
        int size = 5;
        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }
        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }
        model.addAttribute("products", productService.listAllProducts(PageRequest.of(page, size)));
        return "adminproduct";
    }
    // Add
    @GetMapping("/cart/reduce/{productcode}")
    public String reduceCartQuantity(@PathVariable String productcode, Model model) throws IOException {
        productService.reduceQuantityFromVirtualCart(productcode);
        productService.increaseStockQuantity(productcode);
        List<ProductVirtualCartDTO> obj = productService.listAllCartItems();
        if (obj != null) {
            model.addAttribute("products", productService.listAllCartItems());
        }
        model.addAttribute("total", productService.totalCharges());
        return "productcart";
    }
    @GetMapping("/cart/more/{productcode}")
    public String moreCartQuantity(@PathVariable String productcode, Model model) throws IOException {
        Product underChange = productService.getProductByProductCode(productcode);
        productService.addItemToVirtualCart(productcode);
        productService.decreaseStockQuantity(productcode);
        List<ProductVirtualCartDTO> obj = productService.listAllCartItems();
        if (obj != null) {
            model.addAttribute("products", productService.listAllCartItems());
        }
        model.addAttribute("total", productService.totalCharges());
        return "productcart";
    }
    @GetMapping("/cart/remove/{productcode}")
    public String removeProductFromCart(@PathVariable String productcode, Model model) throws IOException {
        short returnQuantity = productService.getQuantityFromVirtualCart(productcode);
        productService.increaseStockQuantityBatch(returnQuantity, productcode);
        productService.removeItemFromVirtualCart(productcode);
        List<ProductVirtualCartDTO> obj = productService.listAllCartItems();
        if (obj != null) {
            model.addAttribute("products", productService.listAllCartItems());
        }
        model.addAttribute("total", productService.totalCharges());
        return "productcart";
    }
    @PostMapping("/add")
    public String saveProduct(@ModelAttribute("product") Product product, BindingResult result, Model model) {
        //TODO exception handling if product already exists
        productService.saveProduct(product);
        return "redirect:/product/admin";
    }
    @GetMapping("/delete/{productcode}")
    public String deleteProduct(@PathVariable String productcode) {
        productService.deleteProduct(productcode);
        return "redirect:/product/admin";
    }
    @GetMapping("/add")
    public String addProduct(Model model) {
        Set<String> productcodes = new HashSet<>();
       // for (ProductLine pl : productLineService.findAllProductLine()) {
        //    productcodes.add(pl.getProductLine());

       // }
        model.addAttribute("product", new Product());
        model.addAttribute("productlines", productcodes);
        return "productadd";
    }
    @GetMapping("/edit/{productcode}")
    public ModelAndView editProduct(@PathVariable("productcode") String productcode) {
        ModelAndView editview = new ModelAndView("productadd");
        Set<String> productcodes = new HashSet<>();
        //for (ProductLine pl : productLineService.findAllProductLine()) {
        //    productcodes.add(pl.getProductLine());
       // }
        editview.addObject("productlines", productcodes);
        Product product = productService.findProductByProductCode(productcode);
        editview.addObject("product", product);
        return editview;
    }
    @GetMapping("/order")
    public String saveMyOrders(HttpServletRequest request) {
        productService.processMyOrders(request);
        return "redirect:/product/";
    }
    @GetMapping("/search/{productname}")
    public String searchProduct(@PathVariable("productname") String productname, Model model) {
        var x = productService.searchProductByName(productname);
        model.addAttribute("products", x);
        return "productlist";

    }
*/
}