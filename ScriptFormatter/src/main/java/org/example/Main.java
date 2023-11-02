package org.example;
import java.util.*;

public class Main {


    public static String formatPlayScript(String[] roles, String[] textLines) {
        Map<String, List<String>> roleLines = new LinkedHashMap<>();
        String currentRole = null;
        int lineNumber = 0;

        for (String line : textLines) {
            lineNumber++;
            String role = getRoleFromLine(roles, line);
            String text = line.substring(role.length() + 2);

            if (!roleLines.containsKey(role)) {
                roleLines.put(role, new ArrayList<>());
            }

            roleLines.get(role).add(lineNumber + ") " + text);
            currentRole = role;
        }

        StringBuilder result = new StringBuilder();
        for (String role : roles) {
            if (roleLines.containsKey(role)) {
                result.append(role).append(":\n");
                result.append(String.join("\n", roleLines.get(role))).append("\n\n");
            }
        }

        return result.toString();
    }

    private static String getRoleFromLine(String[] roles, String line) {
        for (String role : roles) {
            if (line.startsWith(role)) {
                return role;
            }
        }
        return "";
    }


    public static void main(String[] args) {
        String[] roles = {
                "Городничий",
                "Аммос Федорович",
                "Артемий Филиппович",
                "Лука Лукич"
        };

        String[] textLines = {
                "Городничий: Я пригласил вас, господа, с тем, чтобы сообщить вам пренеприятное известие: к нам едет ревизор.",
                "Аммос Федорович: Как ревизор?",
                "Артемий Филиппович: Как ревизор?",
                "Городничий: Ревизор из Петербурга, инкогнито. И еще с секретным предписаньем.",
                "Аммос Федорович: Вот те на!",
                "Артемий Филиппович: Вот не было заботы, так подай!",
                "Лука Лукич: Господи боже! еще и с секретным предписаньем!"
        };

        String formattedScript = formatPlayScript(roles, textLines);
        System.out.println(formattedScript);
    }
}