package com.jbk.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jbk.dao.ProductDao;
import com.jbk.entity.Category;
import com.jbk.entity.Product;
import com.jbk.entity.Supplier;
import com.jbk.model.Charges;
import com.jbk.model.FinalProduct;
import com.jbk.service.ProductService;



@Service
public class ProductServiceIMPL implements ProductService {

	@Autowired
	private ProductDao dao;

	@Override
	public boolean saveProduct(Product product) {
		String productId = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS").format(LocalDateTime.now());
		product.setProductId(productId);
		boolean isAdded = dao.saveProduct(product);
		return isAdded;
	}

	@Override
	public Product getProductById(String productId) {

		return dao.getProductById(productId);
	}

	@Override
	public List<Product> getAllProducts() {

		return dao.getAllProducts();
	}

	@Override
	public boolean deleteProductById(String productId) {
		return dao.deleteProductById(productId);
	}

	@Override
	public boolean updateProduct(Product product) {
		return dao.updateProduct(product);
	}

	@Override
	public List<Product> sortProductsById_ASC() {
		return dao.sortProductsById_ASC();
	}

	@Override
	public List<Product> sortProductsByName_DESC() {

		return dao.sortProductsByName_DESC();
	}

	@Override
	public List<Product> getMaxPriceProducts() {
		return dao.getMaxPriceProducts();
	}

	@Override
	public double getMaxPrice() {
		return dao.getMaxPrice();
	}

	@Override
	public double countSumOfProductPrice() {
		return dao.countSumOfProductPrice();
	}

	@Override
	public int getTotalCountOfProducts() {
		return dao.getTotalCountOfProducts();

	}

	@Override
	public FinalProduct getFinalProductById(String productId) {
		Product product = dao.getProductById(productId);

		FinalProduct finalProduct = new FinalProduct();

		Charges charges = new Charges();
		charges.setGst(product.getCategory().getGst());
		charges.setDeliveryCharge(product.getCategory().getDeliveryCharge());

		// calculate

		double discountAmount = (product.getProductPrice() * product.getCategory().getDiscount()) / 100;
		double gstAmount = (product.getProductPrice() * product.getCategory().getGst()) / 100;

		finalProduct.setProductId(productId);
		finalProduct.setProductName(product.getProductName());
		finalProduct.setSupplier(product.getSupplier());
		finalProduct.setCategory(product.getCategory());
		finalProduct.setProductQty(product.getProductQty());
		finalProduct.setProductPrice(product.getProductPrice());
		finalProduct.setCharges(charges);

		finalProduct.setDiscountAmount(discountAmount);
		finalProduct.setFinalProductPrice(
				(product.getProductPrice() + gstAmount + product.getCategory().getDeliveryCharge()) - discountAmount);

		return finalProduct;
	}
	
	public List<Product> readExcelSheet(String path){
		
		Product product=null;
		List<Product> list= new ArrayList<Product>();
		try {
			FileInputStream fis = new FileInputStream(path);
			
			Workbook workbook = new XSSFWorkbook(fis);
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rows = sheet.rowIterator();
			
			while (rows.hasNext()) {
				Row row = rows.next();
				int rowNum = row.getRowNum();
				if(rowNum==0) {
					continue;
				}
				product = new Product();
				
				Random rand = new Random();
				int randNo= rand.nextInt(90)+10;
				String productId = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS").format(LocalDateTime.now());
				productId=productId+randNo;
				product.setProductId(productId);
				Iterator<Cell> cells = row.cellIterator();
				
				while (cells.hasNext()) {
					Cell cell = cells.next();	
					int columnIndex = cell.getColumnIndex();	
					
					switch (columnIndex) {
						case 0:
						{
							product.setProductName(cell.getStringCellValue());
							break;
						}
						case 1:
						{
							Supplier supplier = new Supplier();
							supplier.setSupplierId((int)cell.getNumericCellValue());
							product.setSupplier(supplier);
							break;
						}
						case 2:
						{
							Category category = new Category();
							category.setCategoryId((int)cell.getNumericCellValue());
							product.setCategory(category);
							break;
						}
						case 3:
						{
							product.setProductQty((int)cell.getNumericCellValue());
							break;
						}
						case 4:
						{
							product.setProductPrice(cell.getNumericCellValue());
							break;
						}
					}
				}
				list.add(product);
				System.out.println(list);
					
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public String uploadSheet(MultipartFile myFile) {
		
		File file = new File("src/main/resources");
		String msg = null;
		String path = file.getAbsolutePath();
		
		try {
			byte[] data = myFile.getBytes();
			FileOutputStream fos = new FileOutputStream(new File(path+file.separator+myFile.getOriginalFilename()));
			fos.write(data);
			
			List<Product> list =readExcelSheet(path+file.separator+myFile.getOriginalFilename());
			msg = dao.uploadProducts(list);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return msg;
	}

	@Override
	public String exportToExcel(HttpSession session) {
		List<Product> allProduct = getAllProducts();
//		System.out.println("All product :: "+allProduct);
		String filePath = null;
		String[] columns = { "productID", "productName", "productQTY", "productPrice", "categoryID","supplierID" };
		try {


			Workbook workbook = new XSSFWorkbook(); 
			Sheet sheet = workbook.createSheet("product");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setFontHeightInPoints((short) 14);
			headerFont.setColor(IndexedColors.BROWN.getIndex());


			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			Row headerRow = sheet.createRow(0);

			for (int i = 0; i < columns.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(columns[i]);
				cell.setCellStyle(headerCellStyle);
			}

			int rowNum = 1;
			for (Product product : allProduct) {
				Row row = sheet.createRow(rowNum++);

				row.createCell(0).setCellValue(product.getProductId());

				row.createCell(1).setCellValue(product.getProductName());

				row.createCell(2).setCellValue(product.getProductQty());

				row.createCell(3).setCellValue(product.getProductPrice());
				
				row.createCell(4).setCellValue(product.getCategory().getCategoryId());

				row.createCell(5).setCellValue(product.getSupplier().getSupplierId());
				
			}

			// Resize all columns to fit the content size
			for (int i = 0; i < columns.length; i++) {
				sheet.autoSizeColumn(i);
			}

			// Write the output to a file
			//path = session.getServletContext().getRealPath("/exported");
			 filePath = "F:/Java JBK Materital/OJT Folder/Advance java";
			 

			filePath=filePath+"/Export";
			
			FileOutputStream fileOut = new FileOutputStream(filePath + File.separator + "product.xlsx");
			workbook.write(fileOut);
			fileOut.close();

			// Closing the workbook
			workbook.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return filePath + File.separator + "product.xlsx";
	}
	

}
