package eclipse.demo.controller;

import eclipse.demo.domain.SoupKitchen;
import eclipse.demo.repository.SoupKitchenRepository;
import eclipse.demo.service.SoupKitchenService;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SoupKitchenController {

    @Autowired
    SoupKitchenRepository soupKitchenRepository;

    @Autowired
    SoupKitchenService soupKitchenService;

    @PostMapping("/excel/read")
    public String readSoupKitchen(@RequestPart MultipartFile file) throws IOException {

        List<SoupKitchen> dataList = new ArrayList<>();

        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        Workbook workbook =null;

        if (extension.equals("xlsx")){
            workbook = new XSSFWorkbook(file.getInputStream());
            System.out.println("XSSworkbook>>" + workbook);
        }else if (extension.equals("xls")){
            workbook = new HSSFWorkbook(file.getInputStream());
            System.out.println("HSSworkbook>>" + workbook);
        }

        Sheet worksheet = workbook.getSheetAt(0);

        for (int i = 2; i < worksheet.getPhysicalNumberOfRows(); i++){

            Row row = worksheet.getRow(i);

            SoupKitchen soupKitchen = new SoupKitchen();

            String[] array = row.getCell(1).getStringCellValue().split("\\s");

            soupKitchen.setName(row.getCell(0).getStringCellValue());
            soupKitchen.setNewAddress(row.getCell(1).getStringCellValue());
            soupKitchen.setOldAddress(row.getCell(2).getStringCellValue());
            soupKitchen.setSiDo(array[0]);
            soupKitchen.setGunGu(array[1]);
            soupKitchen.setOperatingName(row.getCell(3).getStringCellValue());
            soupKitchen.setPhoneNumber(row.getCell(4).getStringCellValue());
            soupKitchen.setLocation(row.getCell(5).getStringCellValue());
            soupKitchen.setTarget(row.getCell(6).getStringCellValue());
            soupKitchen.setTime(row.getCell(7).getStringCellValue());
            soupKitchen.setDoW(row.getCell(8).getStringCellValue());
            soupKitchen.setStartOperation(row.getCell(9).getStringCellValue());
            soupKitchen.setEndOperation(row.getCell(10).getStringCellValue());
            soupKitchen.setLatitude(row.getCell(11).getStringCellValue());
            soupKitchen.setLongitude(row.getCell(12).getStringCellValue());

            soupKitchenRepository.save(soupKitchen);
        }

        return "redirect:/";
    }

    @PostMapping("/selectLocation")
    public String selectLocation(@RequestParam(name = "sido1", required = false) String sido,
                                 @RequestParam(name = "gugun1", required = false) String gungu,
                                 Model model){

        List<SoupKitchen> soupKitchen = soupKitchenService.findSoupKitchen(sido, gungu);

        model.addAttribute("sk", soupKitchen);

        return "/board/editor";
    }




}
