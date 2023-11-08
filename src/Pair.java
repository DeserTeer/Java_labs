public class Pair<integ, value> {



    integ key;
  public  value value;




    public Pair(integ i, value one) {
        Pair.this.key = i;
        Pair.this.value = one;
    }


    public value getValue() {
        return value;
    }
    public void setValue(value value) {
        this.value = value;
    }

    public integ getKey() {
        return key;
    }

    public void setKey(integ key) {
        this.key = key;
    }
}

