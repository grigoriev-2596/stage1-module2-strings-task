package com.epam.mjc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MethodParser {

    static final String ACCESS_MODIFIER_REGEX = "private|public|protected";

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     * 1. access modifier - optional, followed by space: ' '
     * 2. return type - followed by space: ' '
     * 3. method name
     * 4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     * accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     * private void log(String value)
     * Vector3 distort(int x, int y, int z, float magnitude)
     * public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        String[] dataArr = signatureString.split(", |\\)|\\(| ");
        System.out.println(Arrays.deepToString(dataArr));

        String accessModifier = null;
        String methodName;
        String returnType;
        List<MethodSignature.Argument> arguments = new ArrayList<>();

        try {
            int i = 0;
            if (dataArr[i].matches(ACCESS_MODIFIER_REGEX)) {
                accessModifier = dataArr[i];
                i += 1;
            }

            returnType = dataArr[i];
            i += 1;

            methodName = dataArr[i];
            i += 1;

            if (i >= dataArr.length) {
                if (accessModifier != null) return createMethodSignature(methodName, arguments, returnType, accessModifier);
                else return createMethodSignature(methodName, arguments, returnType);
            }

            while (i < dataArr.length) {
                arguments.add(new MethodSignature.Argument(dataArr[i], dataArr[i + 1]));
                i += 2;
            }

            if (accessModifier != null) return createMethodSignature(methodName, arguments, returnType, accessModifier);
            else return createMethodSignature(methodName, arguments, returnType);
        } catch (Exception e) {
            throw new IllegalArgumentException("Signature string is wrong!");
        }

    }


    private MethodSignature createMethodSignature(String methodName, List<MethodSignature.Argument> args, String returnType, String accessModifier) {
        MethodSignature signature = createMethodSignature(methodName, args, returnType);
        signature.setAccessModifier(accessModifier);
        return signature;
    }

    private MethodSignature createMethodSignature(String methodName, List<MethodSignature.Argument> args, String returnType) {
        MethodSignature signature = new MethodSignature(methodName, args);
        signature.setReturnType(returnType);
        return signature;
    }

}
