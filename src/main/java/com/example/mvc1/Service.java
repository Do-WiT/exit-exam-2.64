package com.example.mvc1;

@org.springframework.stereotype.Service
public class Service { // Control

    private static short[] reg = new short[16];
    private static short[] mem = new short[255];

    public static Register process(String order){

        String[] orderList = spiltOrder(order);

        for (String rd: orderList) {
            if (rd.substring(0,2).equals("00")){ // load
                int regId = Integer.parseInt(rd.substring(2, 6), 2);// register index
                int memId = Integer.parseInt(rd.substring(6, 14), 2);  // mem index

                reg[regId] = mem[memId];

            }
            else if (rd.substring(0,2).equals("01")){ // store
                int regId = Integer.parseInt(rd.substring(2, 6), 2);// register index
                int memId = Integer.parseInt(rd.substring(6, 14), 2);  // mem index

                mem[memId] = reg[regId];
            }
            else if (rd.substring(0,2).equals("10")){ // add register
                int regId1 = Integer.parseInt(rd.substring(2, 6), 2);// register1 index
                int regId2 = Integer.parseInt(rd.substring(6, 10), 2);// register2 index

                reg[regId1] = (short) (reg[regId1] + reg[regId2]); // load to register1

            }
            else{ // add Immediate
                int regId = Integer.parseInt(rd.substring(2, 6), 2);// register index
                int constant = Integer.parseInt(rd.substring(6, 16), 2); // constant value

                reg[regId] = (short) (reg[regId] + constant);
            }

        }
        return new Register(reg);
    }

    private static String[] spiltOrder(String order){
        String[] res = new String[order.length()/16];
        for (int i = 0; i < order.length(); i+=16) {
            //System.out.println(order.substring(i,i+16));
            res[i/16] = order.substring(i,i+16);
        }
        return res;
    }




}
