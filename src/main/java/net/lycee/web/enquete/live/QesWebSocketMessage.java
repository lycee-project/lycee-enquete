package net.lycee.web.enquete.live;

import org.springframework.util.CollectionUtils;

import java.util.*;

public record QesWebSocketMessage(
        QesLiveCommand command,
        Map<String, List<String>> headers,
        String body
) {

    public String getHeader(String key, int index) {
        List<String>values = headers.get(key);
        return values != null ? values.get(index) : null;
    }

    public static QesWebSocketMessage create(String payload) {
        String[] tokens = payload.split("\r?\n");

        QesLiveCommand command = QesLiveCommand.fromValue(tokens[0]);

        int i = 1;
        Map<String, List<String>> headers = new HashMap<>();

        do {
            if (tokens[i].isEmpty()) {
                break;
            }
            String [] header = tokens[i].split(":");
            List<String> value = new ArrayList<>();
            value.add(header[1]);
            headers.merge(header[0], value, (a, b) -> {
                a.addAll(b);
                return a;
            });
        } while (++i < tokens.length);

        StringJoiner bodyJoiner = new StringJoiner("\n");

        i++;
        do {
            bodyJoiner.add(tokens[i]);
        } while (++i < tokens.length);

        return new QesWebSocketMessage(
                command,
                CollectionUtils.toMultiValueMap(headers),
                bodyJoiner.toString());
    }
}
