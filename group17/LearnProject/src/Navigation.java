class FileStructureDemo{


    final private String DATABASE = "MyDataBase";
    DataEntry myPerson;

    FileStructureDemo(String name, int age, String cellphone){
        myPerson = new Person(name, age, cellphone);
    }

    interface DataEntry{
        String getCellphone();
        String getName();
    }

    class Person implements DataEntry {

        public Person(String name, int age, String cellphone) {
            this.name = name;
            this.age = age;
            this.cellphone = cellphone;
        }

        private String name;
        private int age;
        private String cellphone;

        public String getCellphone() {
            return cellphone;
        }

        public String getName() {
            return name;
        }

    }
}