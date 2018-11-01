import java.util.Scanner;
/*
Artifacts are the variouse items that can be found in places. Some can be picked up by Characters
Some cannot.
 */
public class Artifact {

    private String name;
    private String desc;
    private int value;
    private int mobility;
    private int keyPattern;

    //main constructor that takes a scanner object to parse game map
    public Artifact(Scanner sc){
        //better remove
        String line = sc.nextLine();
        line = line.replaceAll("//.*", "");
        while (line.equals("")) {
            line = sc.nextLine();
            line = line.replaceAll("//.*", "");
        }
        //better remove //end


        if(!line.substring(0,8).equals("ARTIFACT")){
            System.out.println("Not DIRECTION Error: "+line.substring(0,8));
        }

        int numArtifacts = Integer.parseInt(line.replaceAll("\\D",""));
        //better remove
        line = sc.nextLine();
        line = line.replaceAll("//.*","");
        while(line.equals("")){
            line = sc.nextLine();
            line = line.replaceAll("//.*","");
        }
        //better remove //end

        for(int i = 0; i <numArtifacts;i++){
            int place = Integer.parseInt(line.replaceAll("\\s",""));
            line = sc.nextLine();
            line = line.replaceAll("//.*","");
            String[] s = line.split("\\s\\s*");

            int id = Integer.parseInt(s[0]);
            int value = Integer.parseInt(s[1]);
            int mobility = Integer.parseInt(s[2]);
            int keyPattern = Integer.parseInt(s[3]);
            String name = "";
            for(int j = 4; j<s.length;j++){
                name = name+s[j];
            }
            String desc = "";

            //better remove
            line = sc.nextLine();
            line = line.replaceAll("//.*","");
            while(line.equals("")){
                line = sc.nextLine();
                line = line.replaceAll("//.*","");
            }
            //better remove //end

            //getting the desc
            int numLines = Integer.parseInt(line);
            for(int j = 0; j<numLines;j++){
                line = sc.nextLine();
                desc=desc+" "+line;
            }

            //debug
            //System.out.println("name "+name+" desc "+desc+" value "+value+" mobility "+ mobility+ " keyPattern "+keyPattern+" id "+id);
            if (place == 0){
                place = Place.randomID();
            }
            Artifact temp = new Artifact(name,desc, value, mobility,keyPattern,id);
            if(place < 0){
                Character.getCharacterByID(Math.abs(place)).addToInventory(temp);
            }else if(place >0) {
                Place.places.get(place).addArtifact(temp);
            }else{

                //add to a random place (will implement later
                System.out.println("ERROR: an artifact was not placed. This is not normal");
            }
            //better remove
            line = sc.nextLine();
            line = line.replaceAll("//.*","");
            while(line.equals("")&&sc.hasNextLine()){
                line = sc.nextLine();
                line = line.replaceAll("//.*","");
            }
            //better remove //end
        }
    }

    /*
    used only for hte other contructor the easily create artifacts
     */
    private Artifact(String n, String d, int v, int m, int k,int id){
        name = n;
        desc = d;
        value = v;
        mobility = m;
        keyPattern = k;
    }

    public boolean canMove(){
        return mobility>=0;
    }

    //display the description of the artifact
    public void desc(){
        System.out.println(name+": "+desc);
    }

    //getter for the name
    public String getName(){

        return name;
    }

    //activates the object, whatever that purpose may be. it is currently only used to activate use key for now
    public void use(){
        if(keyPattern!=0){
            usekey();
        }
    }

    //uses the key on the current location
    public void usekey(){
        /*
        if(Game.location.useKey(this)){
            System.out.println("key used");
        }else{
            System.out.println("key didn't unlock anything");
        }
        */

    }

    //checks to make sure its the correct key
    public boolean correctKey(int l){
        return l==keyPattern;
    }

}
