package i11.michalkevicius.deividas.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

public class Product
{
    public Product(ResultSet product) throws SQLException
    {
        if (product == null)
            return;
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

    Product() throws SQLException
    {

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

    Product(Product product, float coefficient)
    {
        setName((product.getName()) + "");
        setKcal((coefficient * product.getKcal()) + "");
        setKj((coefficient * product.getKj()) + "");
        setCarbohydrates((coefficient * product.getCarbohydrates()) + "");
        setFat((coefficient * product.getFat()) + "");
        setProtein((coefficient * product.getProtein()) + "");
        setFiber((coefficient * product.getFiber()) + "");
        setWater((coefficient * product.getWater()) + "");
        setAlcohol((coefficient * product.getAlcohol()) + "");
        setAsh((coefficient * product.getAsh()) + "");
        setMonosacharids((coefficient * product.getMonosacharids()) + "");
        setDisacharids((coefficient * product.getDisacharids()) + "");
        setFakeSuger((coefficient * product.getFakeSuger()) + "");
        setGrain((coefficient * product.getGrain()) + "");
        setSugar((coefficient * product.getSugar()) + "");
        setSuperFat((coefficient * product.getSuperFat()) + "");
        setFat4_10((coefficient * product.getFat4_10()) + "");
        setFat12((coefficient * product.getFat12()) + "");
        setFat14((coefficient * product.getFat14()) + "");
        setFat16((coefficient * product.getFat16()) + "");
        setFat18((coefficient * product.getFat18()) + "");
        setFat20((coefficient * product.getFat20()) + "");
        setMonoSuperFat((coefficient * product.getMonoSuperFat()) + "");
        setFat16_1((coefficient * product.getFat16_1()) + "");
        setFat18_1((coefficient * product.getFat18_1()) + "");
        setPoliSuperFat((coefficient * product.getPoliSuperFat()) + "");
        setFat18_2((coefficient * product.getFat18_2()) + "");
        setFat18_3((coefficient * product.getFat18_3()) + "");
        setFat20_4((coefficient * product.getFat20_4()) + "");
        setEpa((coefficient * product.getEpa()) + "");
        setDpa((coefficient * product.getDpa()) + "");
        setDha((coefficient * product.getDha()) + "");
        setCholesterol((coefficient * product.getCholesterol()) + "");
        setRetinol((coefficient * product.getRetinol()) + "");
        setRetinolequivalent((coefficient * product.getRetinolequivalent()) + "");
        setBetakaroten((coefficient * product.getBetakaroten()) + "");
        setVit_d((coefficient * product.getVit_d()) + "");
        setVit_e((coefficient * product.getVit_e()) + "");
        setVit_k((coefficient * product.getVit_k()) + "");
        setTiamin((coefficient * product.getTiamin()) + "");
        setRiboflavin((coefficient * product.getRiboflavin()) + "");
        setVit_c((coefficient * product.getVit_c()) + "");
        setNiacin((coefficient * product.getNiacin()) + "");
        setNiacinequivalent((coefficient * product.getNiacinequivalent()) + "");
        setVit_b_6((coefficient * product.getVit_b_6()) + "");
        setVit_b_12((coefficient * product.getVit_b_12()) + "");
        setFolate((coefficient * product.getFolate()) + "");
        setPhosphorus((coefficient * product.getPhosphorus()) + "");
        setIodine((coefficient * product.getIodine()) + "");
        setIron((coefficient * product.getIron()) + "");
        setCalcium((coefficient * product.getCalcium()) + "");
        setPotassium((coefficient * product.getPotassium()) + "");
        setMagnesium((coefficient * product.getMagnesium()) + "");
        setSodium((coefficient * product.getSodium()) + "");
        setSalt((coefficient * product.getSalt()) + "");
        setSelenium((coefficient * product.getSelenium()) + "");
        setZinc((coefficient * product.getZinc()) + "");
        setRest((coefficient * product.getRest()) + "");
        setEdible_coefficient((coefficient * product.getEdible_coefficient()) + "");
        setDry_material((coefficient * product.getDry_material()) + "");
        setAnimal_protein((coefficient * product.getAnimal_protein()) + "");
        setNatural_protein((coefficient * product.getNatural_protein()) + "");
        setStarch((coefficient * product.getStarch()) + "");
    }

    public String getName()
    {
        return name.get();
    }

    public void setName(String name)
    {
        this.name.set(name);
    }

    public float getKcal()
    {
        return new Float(kcal.get());
    }

    public void setKcal(String kcal)
    {
        this.kcal.set(kcal);
    }

    public float getKj()
    {
        return new Float(kj.get());
    }

    public void setKj(String kj)
    {
        this.kj.set(kj);
    }

    public float getCarbohydrates()
    {
        return new Float(carbohydrates.get());
    }

    public void setCarbohydrates(String carbohydrates)
    {
        this.carbohydrates.set(carbohydrates);
    }

    public float getFat()
    {
        return new Float(fat.get());
    }

    public void setFat(String fat)
    {
        this.fat.set(fat);
    }

    public float getProtein()
    {
        return new Float(protein.get());
    }

    public void setProtein(String protein)
    {
        this.protein.set(protein);
    }

    public float getFiber()
    {
        return new Float(fiber.get());
    }

    public void setFiber(String fiber)
    {
        this.fiber.set(fiber);
    }

    public float getWater()
    {
        return new Float(water.get());
    }

    public void setWater(String water)
    {
        this.water.set(water);
    }

    public float getAlcohol()
    {
        return new Float(alcohol.get());
    }

    public void setAlcohol(String alcohol)
    {
        this.alcohol.set(alcohol);
    }

    public float getAsh()
    {
        return new Float(ash.get());
    }

    public void setAsh(String ash)
    {
        this.ash.set(ash);
    }

    public float getMonosacharids()
    {
        return new Float(monosacharids.get());
    }

    public void setMonosacharids(String monosacharids)
    {
        this.monosacharids.set(monosacharids);
    }

    public float getDisacharids()
    {
        return new Float(disacharids.get());
    }

    public void setDisacharids(String disacharids)
    {
        this.disacharids.set(disacharids);
    }

    public float getFakeSuger()
    {
        return new Float(fakeSuger.get());
    }

    public void setFakeSuger(String fakeSuger)
    {
        this.fakeSuger.set(fakeSuger);
    }

    public float getGrain()
    {
        return new Float(grain.get());
    }

    public void setGrain(String grain)
    {
        this.grain.set(grain);
    }

    public float getSugar()
    {
        return new Float(sugar.get());
    }

    public void setSugar(String sugar)
    {
        this.sugar.set(sugar);
    }

    public float getSuperFat()
    {
        return new Float(superFat.get());
    }

    public void setSuperFat(String superFat)
    {
        this.superFat.set(superFat);
    }

    public float getFat4_10()
    {
        return new Float(fat4_10.get());
    }

    public void setFat4_10(String fat4_10)
    {
        this.fat4_10.set(fat4_10);
    }

    public float getFat12()
    {
        return new Float(fat12.get());
    }

    public void setFat12(String fat12)
    {
        this.fat12.set(fat12);
    }

    public float getFat14()
    {
        return new Float(fat14.get());
    }

    public void setFat14(String fat14)
    {
        this.fat14.set(fat14);
    }

    public float getFat16()
    {
        return new Float(fat16.get());
    }

    public void setFat16(String fat16)
    {
        this.fat16.set(fat16);
    }

    public float getFat18()
    {
        return new Float(fat18.get());
    }

    public void setFat18(String fat18)
    {
        this.fat18.set(fat18);
    }

    public float getFat20()
    {
        return new Float(fat20.get());
    }

    public void setFat20(String fat20)
    {
        this.fat20.set(fat20);
    }

    public float getMonoSuperFat()
    {
        return new Float(monoSuperFat.get());
    }

    public void setMonoSuperFat(String monoSuperFat)
    {
        this.monoSuperFat.set(monoSuperFat);
    }

    public float getFat16_1()
    {
        return new Float(fat16_1.get());
    }

    public void setFat16_1(String fat16_1)
    {
        this.fat16_1.set(fat16_1);
    }

    public float getFat18_1()
    {
        return new Float(fat18_1.get());
    }

    public void setFat18_1(String fat18_1)
    {
        this.fat18_1.set(fat18_1);
    }

    public float getPoliSuperFat()
    {
        return new Float(poliSuperFat.get());
    }

    public void setPoliSuperFat(String poliSuperFat)
    {
        this.poliSuperFat.set(poliSuperFat);
    }

    public float getFat18_2()
    {
        return new Float(fat18_2.get());
    }

    public void setFat18_2(String fat18_2)
    {
        this.fat18_2.set(fat18_2);
    }

    public float getFat18_3()
    {
        return new Float(fat18_3.get());
    }

    public void setFat18_3(String fat18_3)
    {
        this.fat18_3.set(fat18_3);
    }

    public float getFat20_4()
    {
        return new Float(fat20_4.get());
    }

    public void setFat20_4(String fat20_4)
    {
        this.fat20_4.set(fat20_4);
    }

    public float getEpa()
    {
        return new Float(epa.get());
    }

    public void setEpa(String epa)
    {
        this.epa.set(epa);
    }

    public float getDpa()
    {
        return new Float(dpa.get());
    }

    public void setDpa(String dpa)
    {
        this.dpa.set(dpa);
    }

    public float getDha()
    {
        return new Float(dha.get());
    }

    public void setDha(String dha)
    {
        this.dha.set(dha);
    }

    public float getCholesterol()
    {
        return new Float(cholesterol.get());
    }

    public void setCholesterol(String cholesterol)
    {
        this.cholesterol.set(cholesterol);
    }

    public float getRetinol()
    {
        return new Float(retinol.get());
    }

    public void setRetinol(String retinol)
    {
        this.retinol.set(retinol);
    }

    public float getRetinolequivalent()
    {
        return new Float(retinolequivalent.get());
    }

    public void setRetinolequivalent(String retinolequivalent)
    {
        this.retinolequivalent.set(retinolequivalent);
    }

    public float getBetakaroten()
    {
        return new Float(betakaroten.get());
    }

    public void setBetakaroten(String betakaroten)
    {
        this.betakaroten.set(betakaroten);
    }

    public float getVit_d()
    {
        return new Float(vit_d.get());
    }

    public void setVit_d(String vit_d)
    {
        this.vit_d.set(vit_d);
    }

    public float getVit_e()
    {
        return new Float(vit_e.get());
    }

    public void setVit_e(String vit_e)
    {
        this.vit_e.set(vit_e);
    }

    public float getVit_k()
    {
        return new Float(vit_k.get());
    }

    public void setVit_k(String vit_k)
    {
        this.vit_k.set(vit_k);
    }

    public float getTiamin()
    {
        return new Float(tiamin.get());
    }

    public void setTiamin(String tiamin)
    {
        this.tiamin.set(tiamin);
    }

    public float getRiboflavin()
    {
        return new Float(riboflavin.get());
    }

    public void setRiboflavin(String riboflavin)
    {
        this.riboflavin.set(riboflavin);
    }

    public float getVit_c()
    {
        return new Float(vit_c.get());
    }

    public void setVit_c(String vit_c)
    {
        this.vit_c.set(vit_c);
    }

    public float getNiacin()
    {
        return new Float(niacin.get());
    }

    public void setNiacin(String niacin)
    {
        this.niacin.set(niacin);
    }

    public float getNiacinequivalent()
    {
        return new Float(niacinequivalent.get());
    }

    public void setNiacinequivalent(String niacinequivalent)
    {
        this.niacinequivalent.set(niacinequivalent);
    }

    public float getVit_b_6()
    {
        return new Float(vit_b_6.get());
    }

    public void setVit_b_6(String vit_b_6)
    {
        this.vit_b_6.set(vit_b_6);
    }

    public float getVit_b_12()
    {
        return new Float(vit_b_12.get());
    }

    public void setVit_b_12(String vit_b_12)
    {
        this.vit_b_12.set(vit_b_12);
    }

    public float getFolate()
    {
        return new Float(folate.get());
    }

    public void setFolate(String folate)
    {
        this.folate.set(folate);
    }

    public float getPhosphorus()
    {
        return new Float(phosphorus.get());
    }

    public void setPhosphorus(String phosphorus)
    {
        this.phosphorus.set(phosphorus);
    }

    public float getIodine()
    {
        return new Float(iodine.get());
    }

    public void setIodine(String iodine)
    {
        this.iodine.set(iodine);
    }

    public float getIron()
    {
        return new Float(iron.get());
    }

    public void setIron(String iron)
    {
        this.iron.set(iron);
    }

    public float getCalcium()
    {
        return new Float(calcium.get());
    }

    public void setCalcium(String calcium)
    {
        this.calcium.set(calcium);
    }

    public float getPotassium()
    {
        return new Float(potassium.get());
    }

    public void setPotassium(String potassium)
    {
        this.potassium.set(potassium);
    }

    public float getMagnesium()
    {
        return new Float(magnesium.get());
    }

    public void setMagnesium(String magnesium)
    {
        this.magnesium.set(magnesium);
    }

    public float getSodium()
    {
        return new Float(sodium.get());
    }

    public void setSodium(String sodium)
    {
        this.sodium.set(sodium);
    }

    public float getSalt()
    {
        return new Float(salt.get());
    }

    public void setSalt(String salt)
    {
        this.salt.set(salt);
    }

    public float getSelenium()
    {
        return new Float(selenium.get());
    }

    public void setSelenium(String selenium)
    {
        this.selenium.set(selenium);
    }

    public float getZinc()
    {
        return new Float(zinc.get());
    }

    public void setZinc(String zinc)
    {
        this.zinc.set(zinc);
    }

    public float getRest()
    {
        return new Float(rest.get());
    }

    public void setRest(String rest)
    {
        this.rest.set(rest);
    }

    public float getEdible_coefficient()
    {
        return new Float(edible_coefficient.get());
    }

    public void setEdible_coefficient(String edible_coefficient)
    {
        this.edible_coefficient.set(edible_coefficient);
    }

    public float getDry_material()
    {
        return new Float(dry_material.get());
    }

    public void setDry_material(String dry_material)
    {
        this.dry_material.set(dry_material);
    }

    public float getAnimal_protein()
    {
        return new Float(animal_protein.get());
    }

    public void setAnimal_protein(String animal_protein)
    {
        this.animal_protein.set(animal_protein);
    }

    public float getNatural_protein()
    {
        return new Float(natural_protein.get());
    }

    public void setNatural_protein(String natural_protein)
    {
        this.natural_protein.set(natural_protein);
    }

    public float getStarch()
    {
        return new Float(starch.get());
    }

    public void setStarch(String starch)
    {
        this.starch.set(starch);
    }
}
