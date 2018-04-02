package i11.michalkevicius.deividas.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Product
{
    private String id = "";
    public Product(ResultSet product) throws SQLException
    {
        if (product == null)
            return;
        setId(product.getInt("id") + "");
        setName(product.getString("Pavadinimas"));
        setKcal(product.getFloat("Energija (kcal)") + "");
        setKj(product.getFloat("Energija (kJ)") + "");
        setCarbohydrates(product.getFloat("Angliavandeniai (g)") + "");
        setFat(product.getFloat("Riebalai (g)") + "");
        setProtein(product.getFloat("Baltymai (g)") + "");
        setFiber(product.getFloat("Skaidulinės medžiagos(g)") + "");
        setWater(product.getFloat("Vanduo (g)") + "");
        setAlcohol(product.getFloat("Alkoholis (g)") + "");
        setAsh(product.getFloat("Pelenai (g)") + "");
        setMonosacharids(product.getFloat("Monosacharidai (g)") + "");
        setDisacharids(product.getFloat("Disacharidai (g)") + "");
        setFakeSuger(product.getFloat("Saharozė (g)") + "");
        setGrain(product.getFloat("Viso grūdo, viso (g)") + "");
        setSugar(product.getFloat("Cukrus, viso (g)") + "");
        setSuperFat(product.getFloat("Sočiosios riebiosios rūgštys (g)") + "");
        setFat4_10(product.getFloat("Riebiosios rūgštys 4:0-10:0 (g)") + "");
        setFat12(product.getFloat("Riebiosios rūgštys 12:0 (g)") + "");
        setFat14(product.getFloat("Riebiosios rūgštys 14:0 (g)") + "");
        setFat16(product.getFloat("Riebiosios rūgštys 16:0 (g)") + "");
        setFat18(product.getFloat("Riebiosios rūgštys 18:0 (g)") + "");
        setFat20(product.getFloat("Riebiosios rūgštys 20:0 (g)") + "");
        setMonoSuperFat(product.getFloat("Mononesočiosios riebiosios rūgštys(g)") + "");
        setFat16_1(product.getFloat("Riebiosios rūgštys 16:1 (g)") + "");
        setFat18_1(product.getFloat("Riebiosios rūgštys 18:1 (g)") + "");
        setPoliSuperFat(product.getFloat("Polinesočiosios riebiosios rūgštys (g)") + "");
        setFat18_2(product.getFloat("Riebiosios rūgštys 18:2 (g)") + "");
        setFat18_3(product.getFloat("Riebiosios rūgštys 18:3 (g)") + "");
        setFat20_4(product.getFloat("Riebiosios rūgštys 20:4 (g)") + "");
        setEpa(product.getFloat("EPA (Riebiosios rūgštys 20:5) (g)") + "");
        setDpa(product.getFloat("DPA (Riebiosios rūgštys 22:5) (g)") + "");
        setDha(product.getFloat("DHA (Riebiosios rūgštys 22:6) (g)") + "");
        setCholesterol(product.getFloat("Cholesterolis (mg)") + "");
        setRetinol(product.getFloat("Retinolis (µg)") + "");
        setRetinolequivalent(product.getFloat("Retinol ekvivalentas (µg)") + "");
        setBetakaroten(product.getFloat("Beta-karotenas (µg)") + "");
        setVit_d(product.getFloat("Vitaminas D (µg)") + "");
        setVit_e(product.getFloat("Vitaminas E (mg)") + "");
        setVit_k(product.getFloat("Vitaminas K (µg)") + "");
        setTiamin(product.getFloat("Tiaminas (mg)") + "");
        setRiboflavin(product.getFloat("Riboflavinas (mg)") + "");
        setVit_c(product.getFloat("Vitaminas C (mg)") + "");
        setNiacin(product.getFloat("Niacinas (mg)") + "");
        setNiacinequivalent(product.getFloat("Niacino ekvivalentas (mg)") + "");
        setVit_b_6(product.getFloat("Vitaminas B-6 (mg)") + "");
        setVit_b_12(product.getFloat("Vitaminas B-12 (µg)") + "");
        setFolate(product.getFloat("Folatas (µg)") + "");
        setPhosphorus(product.getFloat("Fosforas (mg)") + "");
        setIodine(product.getFloat("Jodas (µg)") + "");
        setIron(product.getFloat("Geležis (mg)") + "");
        setCalcium(product.getFloat("Kalcis (mg)") + "");
        setPotassium(product.getFloat("Kalis (mg)") + "");
        setMagnesium(product.getFloat("Magnis (mg)") + "");
        setSodium(product.getFloat("Natris (mg)") + "");
        setSalt(product.getFloat("Druska (g)") + "");
        setSelenium(product.getFloat("Selenas (µg)") + "");
        setZinc(product.getFloat("Cinkas (mg)") + "");
        setRest(product.getFloat("Atliekos (pvz. lupenos) (%)") + "");
        setEdible_coefficient(product.getFloat("Valgomosios dalies koeficientas") + "");
        setDry_material(product.getFloat("Sausųjų medžiagų (g)") + "");
        setAnimal_protein(product.getFloat("Gyvūninių baltymų (g)") + "");
        setNatural_protein(product.getFloat("Augalinių baltymų (g)") + "");
        setStarch(product.getFloat("Krakmolo (g)") + "");
    }
    private StringProperty name = new SimpleStringProperty("");
    private StringProperty kcal = new SimpleStringProperty("");
    private StringProperty kj = new SimpleStringProperty("");
    private StringProperty carbohydrates = new SimpleStringProperty("");
    private StringProperty fat = new SimpleStringProperty("");
    private StringProperty protein = new SimpleStringProperty("");
    private StringProperty fiber = new SimpleStringProperty("");
    private StringProperty water = new SimpleStringProperty("");
    private StringProperty alcohol = new SimpleStringProperty("");
    private StringProperty ash = new SimpleStringProperty("");
    private StringProperty monosacharids = new SimpleStringProperty("");
    private StringProperty disacharids = new SimpleStringProperty("");
    private StringProperty fakeSuger = new SimpleStringProperty("");
    private StringProperty grain = new SimpleStringProperty("");
    private StringProperty sugar = new SimpleStringProperty("");
    private StringProperty superFat = new SimpleStringProperty("");
    private StringProperty fat4_10 = new SimpleStringProperty("");
    private StringProperty fat12 = new SimpleStringProperty("");
    private StringProperty fat14 = new SimpleStringProperty("");
    private StringProperty fat16 = new SimpleStringProperty("");
    private StringProperty fat18 = new SimpleStringProperty("");
    private StringProperty fat20 = new SimpleStringProperty("");
    private StringProperty monoSuperFat = new SimpleStringProperty("");
    private StringProperty fat16_1 = new SimpleStringProperty("");
    private StringProperty fat18_1 = new SimpleStringProperty("");
    private StringProperty poliSuperFat = new SimpleStringProperty("");
    private StringProperty fat18_2 = new SimpleStringProperty("");
    private StringProperty fat18_3 = new SimpleStringProperty("");
    private StringProperty fat20_4 = new SimpleStringProperty("");
    private StringProperty epa = new SimpleStringProperty("");
    private StringProperty dpa = new SimpleStringProperty("");
    private StringProperty dha = new SimpleStringProperty("");
    private StringProperty cholesterol = new SimpleStringProperty("");
    private StringProperty retinol = new SimpleStringProperty("");
    private StringProperty retinolequivalent = new SimpleStringProperty("");
    private StringProperty betakaroten = new SimpleStringProperty("");
    private StringProperty vit_d = new SimpleStringProperty("");
    private StringProperty vit_e = new SimpleStringProperty("");
    private StringProperty vit_k = new SimpleStringProperty("");
    private StringProperty tiamin = new SimpleStringProperty("");
    private StringProperty riboflavin = new SimpleStringProperty("");
    private StringProperty vit_c = new SimpleStringProperty("");
    private StringProperty niacin = new SimpleStringProperty("");
    private StringProperty niacinequivalent = new SimpleStringProperty("");
    private StringProperty vit_b_6 = new SimpleStringProperty("");
    private StringProperty vit_b_12 = new SimpleStringProperty("");
    private StringProperty folate = new SimpleStringProperty("");
    private StringProperty phosphorus = new SimpleStringProperty("");
    private StringProperty iodine = new SimpleStringProperty("");
    private StringProperty iron = new SimpleStringProperty("");
    private StringProperty calcium = new SimpleStringProperty("");
    private StringProperty potassium = new SimpleStringProperty("");
    private StringProperty magnesium = new SimpleStringProperty("");
    private StringProperty sodium = new SimpleStringProperty("");
    private StringProperty salt = new SimpleStringProperty("");
    private StringProperty selenium = new SimpleStringProperty("");
    private StringProperty zinc = new SimpleStringProperty("");
    private StringProperty rest = new SimpleStringProperty("");
    private StringProperty edible_coefficient = new SimpleStringProperty("");
    private StringProperty dry_material = new SimpleStringProperty("");
    private StringProperty animal_protein = new SimpleStringProperty("");
    private StringProperty natural_protein = new SimpleStringProperty("");
    private StringProperty starch = new SimpleStringProperty("");

    public Product()
    {

    }

    public Product(Product product, float coefficient)
    {
        setId(product.getId());
        setName((product.getName()));
        setKcal((multiply(coefficient, product.getKcal())));
        setKj((multiply(coefficient, product.getKj())));
        setCarbohydrates((multiply(coefficient, product.getCarbohydrates())));
        setFat((multiply(coefficient, product.getFat())));
        setProtein((multiply(coefficient, product.getProtein())));
        setFiber((multiply(coefficient, product.getFiber())));
        setWater((multiply(coefficient, product.getWater())));
        setAlcohol((multiply(coefficient, product.getAlcohol())));
        setAsh((multiply(coefficient, product.getAsh())));
        setMonosacharids((multiply(coefficient, product.getMonosacharids())));
        setDisacharids((multiply(coefficient, product.getDisacharids())));
        setFakeSuger((multiply(coefficient, product.getFakeSuger())));
        setGrain((multiply(coefficient, product.getGrain())));
        setSugar((multiply(coefficient, product.getSugar())));
        setSuperFat((multiply(coefficient, product.getSuperFat())));
        setFat4_10((multiply(coefficient, product.getFat4_10())));
        setFat12((multiply(coefficient, product.getFat12())));
        setFat14((multiply(coefficient, product.getFat14())));
        setFat16((multiply(coefficient, product.getFat16())));
        setFat18((multiply(coefficient, product.getFat18())));
        setFat20((multiply(coefficient, product.getFat20())));
        setMonoSuperFat((multiply(coefficient, product.getMonoSuperFat())));
        setFat16_1((multiply(coefficient, product.getFat16_1())));
        setFat18_1((multiply(coefficient, product.getFat18_1())));
        setPoliSuperFat((multiply(coefficient, product.getPoliSuperFat())));
        setFat18_2((multiply(coefficient, product.getFat18_2())));
        setFat18_3((multiply(coefficient, product.getFat18_3())));
        setFat20_4((multiply(coefficient, product.getFat20_4())));
        setEpa((multiply(coefficient, product.getEpa())));
        setDpa((multiply(coefficient, product.getDpa())));
        setDha((multiply(coefficient, product.getDha())));
        setCholesterol((multiply(coefficient, product.getCholesterol())));
        setRetinol((multiply(coefficient, product.getRetinol())));
        setRetinolequivalent((multiply(coefficient, product.getRetinolequivalent())));
        setBetakaroten((multiply(coefficient, product.getBetakaroten())));
        setVit_d((multiply(coefficient, product.getVit_d())));
        setVit_e((multiply(coefficient, product.getVit_e())));
        setVit_k((multiply(coefficient, product.getVit_k())));
        setTiamin((multiply(coefficient, product.getTiamin())));
        setRiboflavin((multiply(coefficient, product.getRiboflavin())));
        setVit_c((multiply(coefficient, product.getVit_c())));
        setNiacin((multiply(coefficient, product.getNiacin())));
        setNiacinequivalent((multiply(coefficient, product.getNiacinequivalent())));
        setVit_b_6((multiply(coefficient, product.getVit_b_6())));
        setVit_b_12((multiply(coefficient, product.getVit_b_12())));
        setFolate((multiply(coefficient, product.getFolate())));
        setPhosphorus((multiply(coefficient, product.getPhosphorus())));
        setIodine((multiply(coefficient, product.getIodine())));
        setIron((multiply(coefficient, product.getIron())));
        setCalcium((multiply(coefficient, product.getCalcium())));
        setPotassium((multiply(coefficient, product.getPotassium())));
        setMagnesium((multiply(coefficient, product.getMagnesium())));
        setSodium((multiply(coefficient, product.getSodium())));
        setSalt((multiply(coefficient, product.getSalt())));
        setSelenium((multiply(coefficient, product.getSelenium())));
        setZinc((multiply(coefficient, product.getZinc())));
        setRest((multiply(coefficient, product.getRest())));
        setEdible_coefficient((multiply(coefficient, product.getEdible_coefficient())));
        setDry_material((multiply(coefficient, product.getDry_material())));
        setAnimal_protein((multiply(coefficient, product.getAnimal_protein())));
        setNatural_protein((multiply(coefficient, product.getNatural_protein())));
        setStarch((multiply(coefficient, product.getStarch())));
    }

    private static String multiply(float coefficient, String v)
    {
        double value = new Double(v);

        return (value * coefficient) + "";
    }

    public StringProperty nameProperty()
    {
        return name;
    }

    public StringProperty kcalProperty()
    {
        return kcal;
    }

    public StringProperty kjProperty()
    {
        return kj;
    }

    public StringProperty carbohydratesProperty()
    {
        return carbohydrates;
    }

    public StringProperty fatProperty()
    {
        return fat;
    }

    public StringProperty proteinProperty()
    {
        return protein;
    }

    public StringProperty fiberProperty()
    {
        return fiber;
    }

    public StringProperty waterProperty()
    {
        return water;
    }

    public StringProperty alcoholProperty()
    {
        return alcohol;
    }

    public StringProperty ashProperty()
    {
        return ash;
    }

    public StringProperty monosacharidsProperty()
    {
        return monosacharids;
    }

    public StringProperty disacharidsProperty()
    {
        return disacharids;
    }

    public StringProperty fakeSugerProperty()
    {
        return fakeSuger;
    }

    public StringProperty grainProperty()
    {
        return grain;
    }

    public StringProperty sugarProperty()
    {
        return sugar;
    }

    public StringProperty superFatProperty()
    {
        return superFat;
    }

    public StringProperty fat4_10Property()
    {
        return fat4_10;
    }

    public StringProperty fat12Property()
    {
        return fat12;
    }

    public StringProperty fat14Property()
    {
        return fat14;
    }

    public StringProperty fat16Property()
    {
        return fat16;
    }

    public StringProperty fat18Property()
    {
        return fat18;
    }

    public StringProperty fat20Property()
    {
        return fat20;
    }

    public StringProperty monoSuperFatProperty()
    {
        return monoSuperFat;
    }

    public StringProperty fat16_1Property()
    {
        return fat16_1;
    }

    public StringProperty fat18_1Property()
    {
        return fat18_1;
    }

    public StringProperty poliSuperFatProperty()
    {
        return poliSuperFat;
    }

    public StringProperty fat18_2Property()
    {
        return fat18_2;
    }

    public StringProperty fat18_3Property()
    {
        return fat18_3;
    }

    public StringProperty fat20_4Property()
    {
        return fat20_4;
    }

    public StringProperty epaProperty()
    {
        return epa;
    }

    public StringProperty dpaProperty()
    {
        return dpa;
    }

    public StringProperty dhaProperty()
    {
        return dha;
    }

    public StringProperty cholesterolProperty()
    {
        return cholesterol;
    }

    public StringProperty retinolProperty()
    {
        return retinol;
    }

    public StringProperty retinolequivalentProperty()
    {
        return retinolequivalent;
    }

    public StringProperty betakarotenProperty()
    {
        return betakaroten;
    }

    public StringProperty vit_dProperty()
    {
        return vit_d;
    }

    public StringProperty vit_eProperty()
    {
        return vit_e;
    }

    public StringProperty vit_kProperty()
    {
        return vit_k;
    }

    public StringProperty tiaminProperty()
    {
        return tiamin;
    }

    public StringProperty riboflavinProperty()
    {
        return riboflavin;
    }

    public StringProperty vit_cProperty()
    {
        return vit_c;
    }

    public StringProperty niacinProperty()
    {
        return niacin;
    }

    public StringProperty niacinequivalentProperty()
    {
        return niacinequivalent;
    }

    public StringProperty vit_b_6Property()
    {
        return vit_b_6;
    }

    public StringProperty vit_b_12Property()
    {
        return vit_b_12;
    }

    public StringProperty folateProperty()
    {
        return folate;
    }

    public StringProperty phosphorusProperty()
    {
        return phosphorus;
    }

    public StringProperty iodineProperty()
    {
        return iodine;
    }

    public StringProperty ironProperty()
    {
        return iron;
    }

    public StringProperty calciumProperty()
    {
        return calcium;
    }

    public StringProperty potassiumProperty()
    {
        return potassium;
    }

    public StringProperty magnesiumProperty()
    {
        return magnesium;
    }

    public StringProperty sodiumProperty()
    {
        return sodium;
    }

    public StringProperty saltProperty()
    {
        return salt;
    }

    public StringProperty seleniumProperty()
    {
        return selenium;
    }

    public StringProperty zincProperty()
    {
        return zinc;
    }

    public StringProperty restProperty()
    {
        return rest;
    }

    public StringProperty edible_coefficientProperty()
    {
        return edible_coefficient;
    }

    public StringProperty dry_materialProperty()
    {
        return dry_material;
    }

    public StringProperty animal_proteinProperty()
    {
        return animal_protein;
    }

    public static ArrayList<String> propertyNames()
    {
        ArrayList<String> names = new ArrayList<>();
        Collections.addAll(names,
                "name",
                "kcal",
                "kj",
                "carbohydrates",
                "fat",
                "protein",
                "fiber",
                "water",
                "alcohol",
                "ash",
                "monosacharids",
                "disacharids",
                "fakeSuger",
                "grain",
                "sugar",
                "superFat",
                "fat4_10",
                "fat12",
                "fat14",
                "fat16",
                "fat18",
                "fat20",
                "monoSuperFat",
                "fat16_1",
                "fat18_1",
                "poliSuperFat",
                "fat18_2",
                "fat18_3",
                "fat20_4",
                "epa",
                "dpa",
                "dha",
                "cholesterol",
                "retinol",
                "retinolequivalent",
                "betakaroten",
                "vit_d",
                "vit_e",
                "vit_k",
                "tiamin",
                "riboflavin",
                "vit_c",
                "niacin",
                "niacinequivalent",
                "vit_b_6",
                "vit_b_12",
                "folate",
                "phosphorus",
                "iodine",
                "iron",
                "calcium",
                "potassium",
                "magnesium",
                "sodium",
                "salt",
                "selenium",
                "zinc",
                "rest",
                "edible_coefficient",
                "dry_material",
                "animal_protein",
                "natural_protein",
                "starch");
        return names;
    }

    public StringProperty natural_proteinProperty()
    {
        return natural_protein;
    }

    public StringProperty starchProperty()
    {
        return starch;
    }

    @Override
    public String toString()
    {
        return getName();
    }

    public String getName()
    {
        return name.get();
    }

    public void setName(String name)
    {
        this.name.set(name);
    }

    public String getKcal()
    {
        return kcal.get();
    }

    public void setKcal(String kcal)
    {
        this.kcal.set(kcal);
    }

    public String getKj()
    {
        return kj.get();
    }

    public void setKj(String kj)
    {
        this.kj.set(kj);
    }

    public String getCarbohydrates()
    {
        return carbohydrates.get();
    }

    public void setCarbohydrates(String carbohydrates)
    {
        this.carbohydrates.set(carbohydrates);
    }

    public String getFat()
    {
        return fat.get();
    }

    public void setFat(String fat)
    {
        this.fat.set(fat);
    }

    public String getProtein()
    {
        return protein.get();
    }

    public void setProtein(String protein)
    {
        this.protein.set(protein);
    }

    public String getFiber()
    {
        return fiber.get();
    }

    public void setFiber(String fiber)
    {
        this.fiber.set(fiber);
    }

    public String getWater()
    {
        return water.get();
    }

    public void setWater(String water)
    {
        this.water.set(water);
    }

    public String getAlcohol()
    {
        return alcohol.get();
    }

    public void setAlcohol(String alcohol)
    {
        this.alcohol.set(alcohol);
    }

    public String getAsh()
    {
        return ash.get();
    }

    public void setAsh(String ash)
    {
        this.ash.set(ash);
    }

    public String getMonosacharids()
    {
        return monosacharids.get();
    }

    public void setMonosacharids(String monosacharids)
    {
        this.monosacharids.set(monosacharids);
    }

    public String getDisacharids()
    {
        return disacharids.get();
    }

    public void setDisacharids(String disacharids)
    {
        this.disacharids.set(disacharids);
    }

    public String getFakeSuger()
    {
        return fakeSuger.get();
    }

    public void setFakeSuger(String fakeSuger)
    {
        this.fakeSuger.set(fakeSuger);
    }

    public String getGrain()
    {
        return grain.get();
    }

    public void setGrain(String grain)
    {
        this.grain.set(grain);
    }

    public String getSugar()
    {
        return sugar.get();
    }

    public void setSugar(String sugar)
    {
        this.sugar.set(sugar);
    }

    public String getSuperFat()
    {
        return superFat.get();
    }

    public void setSuperFat(String superFat)
    {
        this.superFat.set(superFat);
    }

    public String getFat4_10()
    {
        return fat4_10.get();
    }

    public void setFat4_10(String fat4_10)
    {
        this.fat4_10.set(fat4_10);
    }

    public String getFat12()
    {
        return fat12.get();
    }

    public void setFat12(String fat12)
    {
        this.fat12.set(fat12);
    }

    public String getFat14()
    {
        return fat14.get();
    }

    public void setFat14(String fat14)
    {
        this.fat14.set(fat14);
    }

    public String getFat16()
    {
        return fat16.get();
    }

    public void setFat16(String fat16)
    {
        this.fat16.set(fat16);
    }

    public String getFat18()
    {
        return fat18.get();
    }

    public void setFat18(String fat18)
    {
        this.fat18.set(fat18);
    }

    public String getFat20()
    {
        return fat20.get();
    }

    public void setFat20(String fat20)
    {
        this.fat20.set(fat20);
    }

    public String getMonoSuperFat()
    {
        return monoSuperFat.get();
    }

    public void setMonoSuperFat(String monoSuperFat)
    {
        this.monoSuperFat.set(monoSuperFat);
    }

    public String getFat16_1()
    {
        return fat16_1.get();
    }

    public void setFat16_1(String fat16_1)
    {
        this.fat16_1.set(fat16_1);
    }

    public String getFat18_1()
    {
        return fat18_1.get();
    }

    public void setFat18_1(String fat18_1)
    {
        this.fat18_1.set(fat18_1);
    }

    public String getPoliSuperFat()
    {
        return poliSuperFat.get();
    }

    public void setPoliSuperFat(String poliSuperFat)
    {
        this.poliSuperFat.set(poliSuperFat);
    }

    public String getFat18_2()
    {
        return fat18_2.get();
    }

    public void setFat18_2(String fat18_2)
    {
        this.fat18_2.set(fat18_2);
    }

    public String getFat18_3()
    {
        return fat18_3.get();
    }

    public void setFat18_3(String fat18_3)
    {
        this.fat18_3.set(fat18_3);
    }

    public String getFat20_4()
    {
        return fat20_4.get();
    }

    public void setFat20_4(String fat20_4)
    {
        this.fat20_4.set(fat20_4);
    }

    public String getEpa()
    {
        return epa.get();
    }

    public void setEpa(String epa)
    {
        this.epa.set(epa);
    }

    public String getDpa()
    {
        return dpa.get();
    }

    public void setDpa(String dpa)
    {
        this.dpa.set(dpa);
    }

    public String getDha()
    {
        return dha.get();
    }

    public void setDha(String dha)
    {
        this.dha.set(dha);
    }

    public String getCholesterol()
    {
        return cholesterol.get();
    }

    public void setCholesterol(String cholesterol)
    {
        this.cholesterol.set(cholesterol);
    }

    public String getRetinol()
    {
        return retinol.get();
    }

    public void setRetinol(String retinol)
    {
        this.retinol.set(retinol);
    }

    public String getRetinolequivalent()
    {
        return retinolequivalent.get();
    }

    public void setRetinolequivalent(String retinolequivalent)
    {
        this.retinolequivalent.set(retinolequivalent);
    }

    public String getBetakaroten()
    {
        return betakaroten.get();
    }

    public void setBetakaroten(String betakaroten)
    {
        this.betakaroten.set(betakaroten);
    }

    public String getVit_d()
    {
        return vit_d.get();
    }

    public void setVit_d(String vit_d)
    {
        this.vit_d.set(vit_d);
    }

    public String getVit_e()
    {
        return vit_e.get();
    }

    public void setVit_e(String vit_e)
    {
        this.vit_e.set(vit_e);
    }

    public String getVit_k()
    {
        return vit_k.get();
    }

    public void setVit_k(String vit_k)
    {
        this.vit_k.set(vit_k);
    }

    public String getTiamin()
    {
        return tiamin.get();
    }

    public void setTiamin(String tiamin)
    {
        this.tiamin.set(tiamin);
    }

    public String getRiboflavin()
    {
        return riboflavin.get();
    }

    public void setRiboflavin(String riboflavin)
    {
        this.riboflavin.set(riboflavin);
    }

    public String getVit_c()
    {
        return vit_c.get();
    }

    public void setVit_c(String vit_c)
    {
        this.vit_c.set(vit_c);
    }

    public String getNiacin()
    {
        return niacin.get();
    }

    public void setNiacin(String niacin)
    {
        this.niacin.set(niacin);
    }

    public String getNiacinequivalent()
    {
        return niacinequivalent.get();
    }

    public void setNiacinequivalent(String niacinequivalent)
    {
        this.niacinequivalent.set(niacinequivalent);
    }

    public String getVit_b_6()
    {
        return vit_b_6.get();
    }

    public void setVit_b_6(String vit_b_6)
    {
        this.vit_b_6.set(vit_b_6);
    }

    public String getVit_b_12()
    {
        return vit_b_12.get();
    }

    public void setVit_b_12(String vit_b_12)
    {
        this.vit_b_12.set(vit_b_12);
    }

    public String getFolate()
    {
        return folate.get();
    }

    public void setFolate(String folate)
    {
        this.folate.set(folate);
    }

    public String getPhosphorus()
    {
        return phosphorus.get();
    }

    public void setPhosphorus(String phosphorus)
    {
        this.phosphorus.set(phosphorus);
    }

    public String getIodine()
    {
        return iodine.get();
    }

    public void setIodine(String iodine)
    {
        this.iodine.set(iodine);
    }

    public String getIron()
    {
        return iron.get();
    }

    public void setIron(String iron)
    {
        this.iron.set(iron);
    }

    public String getCalcium()
    {
        return calcium.get();
    }

    public void setCalcium(String calcium)
    {
        this.calcium.set(calcium);
    }

    public String getPotassium()
    {
        return potassium.get();
    }

    public void setPotassium(String potassium)
    {
        this.potassium.set(potassium);
    }

    public String getMagnesium()
    {
        return magnesium.get();
    }

    public void setMagnesium(String magnesium)
    {
        this.magnesium.set(magnesium);
    }

    public String getSodium()
    {
        return sodium.get();
    }

    public void setSodium(String sodium)
    {
        this.sodium.set(sodium);
    }

    public String getSalt()
    {
        return salt.get();
    }

    public void setSalt(String salt)
    {
        this.salt.set(salt);
    }

    public String getSelenium()
    {
        return selenium.get();
    }

    public void setSelenium(String selenium)
    {
        this.selenium.set(selenium);
    }

    public String getZinc()
    {
        return zinc.get();
    }

    public void setZinc(String zinc)
    {
        this.zinc.set(zinc);
    }

    public String getRest()
    {
        return rest.get();
    }

    public void setRest(String rest)
    {
        this.rest.set(rest);
    }

    public String getEdible_coefficient()
    {
        return edible_coefficient.get();
    }

    public void setEdible_coefficient(String edible_coefficient)
    {
        this.edible_coefficient.set(edible_coefficient);
    }

    public String getDry_material()
    {
        return dry_material.get();
    }

    public void setDry_material(String dry_material)
    {
        this.dry_material.set(dry_material);
    }

    public String getAnimal_protein()
    {
        return animal_protein.get();
    }

    public void setAnimal_protein(String animal_protein)
    {
        this.animal_protein.set(animal_protein);
    }

    public String getNatural_protein()
    {
        return natural_protein.get();
    }

    public void setNatural_protein(String natural_protein)
    {
        this.natural_protein.set(natural_protein);
    }

    public String getStarch()
    {
        return starch.get();
    }

    public void setStarch(String starch)
    {
        this.starch.set(starch);
    }

    public List<StringProperty> bindableProperties()
    {
        List<StringProperty> returnable = new ArrayList<>();
        Collections.addAll(returnable, nameProperty(),
                kcalProperty(),
                kjProperty(),
                carbohydratesProperty(),
                fatProperty(),
                proteinProperty(),
                fiberProperty(),
                waterProperty(),
                alcoholProperty(),
                ashProperty(),
                monosacharidsProperty(),
                disacharidsProperty(),
                fakeSugerProperty(),
                grainProperty(),
                sugarProperty(),
                superFatProperty(),
                fat4_10Property(),
                fat12Property(),
                fat14Property(),
                fat16Property(),
                fat18Property(),
                fat20Property(),
                monoSuperFatProperty(),
                fat16_1Property(),
                fat18_1Property(),
                poliSuperFatProperty(),
                fat18_2Property(),
                fat18_3Property(),
                fat20_4Property(),
                epaProperty(),
                dpaProperty(),
                dhaProperty(),
                cholesterolProperty(),
                retinolProperty(),
                retinolequivalentProperty(),
                betakarotenProperty(),
                vit_dProperty(),
                vit_eProperty(),
                vit_kProperty(),
                tiaminProperty(),
                riboflavinProperty(),
                vit_cProperty(),
                niacinProperty(),
                niacinequivalentProperty(),
                vit_b_6Property(),
                vit_b_12Property(),
                folateProperty(),
                phosphorusProperty(),
                iodineProperty(),
                ironProperty(),
                calciumProperty(),
                potassiumProperty(),
                magnesiumProperty(),
                sodiumProperty(),
                saltProperty(),
                seleniumProperty(),
                zincProperty(),
                restProperty(),
                edible_coefficientProperty(),
                dry_materialProperty(),
                animal_proteinProperty(),
                natural_proteinProperty(),
                starchProperty());
        return returnable;
    }

    public String getId()
    {
        if (id.length() != 0)
            return id;
        else
            return null;
    }

    public void setId(String id)
    {
        this.id = id;
    }
}
