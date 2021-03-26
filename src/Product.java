import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Product implements Serializable {

    //统一日期格式
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public String ProductName;//产品名字
    public Date ManufactureDate;//生产日期
    public int QualityGuaranteePeriod;//保质期长度
    public Boolean State;//是否过期


    public Product(String productName, Date manufactureDate, int qualityGuaranteePeriod, Boolean state) {
        ProductName = productName;
        ManufactureDate = manufactureDate;
        QualityGuaranteePeriod = qualityGuaranteePeriod;
        State = state;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public Date getManufactureDate() {
        return ManufactureDate;
    }

    public void setManufactureDate(Date manufactureDate) {
        ManufactureDate = manufactureDate;
    }

    public int getQualityGuaranteePeriod() {
        return QualityGuaranteePeriod;
    }

    public void setQualityGuaranteePeriod(int qualityGuaranteePeriod) {
        QualityGuaranteePeriod = qualityGuaranteePeriod;
    }

    public Boolean getState() {
        return State;
    }

    public void setState(Boolean state) {
        State = state;
    }

    @Override
    public String toString() {
        return "Product{" +
                "ProductName='" + ProductName + '\'' +
                ", ManufactureDate=" + ManufactureDate +
                ", QualityGuaranteePeriod=" + QualityGuaranteePeriod +
                ", State=" + State +
                '}';
    }

    /**
     * 判断商品是否过期
     * @param product 商品类
     */
    public static void judgeExpired(Product product){
        //获取当前年月日的string
        String currentDateString = sdf.format(new Date());
        Date currentDate = null;
        long daysBetween=0;
        try {
            //将当前年月日string转为date
            currentDate = sdf.parse(currentDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(currentDate!=null){
            //计算生产日期到当前日期的天数
            daysBetween=(currentDate.getTime()-product.getManufactureDate().getTime()+1000000)/(60*60*24*1000);
        }else{
            System.out.println("日期格式出错！");
        }
        //System.out.println("当前日期："+sdf.format(currentDate)+"产品生产日期："+sdf.format(product.ManufactureDate)+"已经过了："+daysBetween+"天");
        if((product.getQualityGuaranteePeriod()-daysBetween)<=0){
            System.out.println("产品已经过期"+(daysBetween-product.getQualityGuaranteePeriod())+"天了~");
            product.setState(true);
        }else if((product.getQualityGuaranteePeriod()-daysBetween)<=14){
            System.out.println("产品还未过期，但是还有"+(product.getQualityGuaranteePeriod()-daysBetween)+"天过期~");
        }else{
            System.out.println("还未到达产品过期的14天倒计时~");
        }

    }

    public static void main(String[] args) {

        //用户输入信息
        System.out.println("输入产品名称，按回车确定：");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.println("输入生产日期(yyyy-mm-dd)，按回车确定：");
        String shengChanRiQiString = scanner.nextLine();
        System.out.println("输入保质期（大于等于14天），按回车确定：");
        int baoZhiQi = scanner.nextInt();
        Boolean flag = false;

        //将生产日期String转为date格式
        Date shengChanRiQi = null;
        try {
            shengChanRiQi = sdf.parse(shengChanRiQiString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Product product = null;
        //判空操作
        if(shengChanRiQi!=null) {
            //构建产品类
            product = new Product(name, shengChanRiQi, baoZhiQi, flag);
        }
        else
            System.out.println("日期格式出错！");
        if(product!=null)
            judgeExpired(product);
        else
            System.out.println("出错！！！");
    }

}
