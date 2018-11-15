package faeteam3.Notlage.notlage;

import faeteam3.Notlage.DVP;
import faeteam3.Notlage.Nachricht;
import faeteam3.Notlage.notlage.repository.NotlageRepository;
import faeteam3.Notlage.notlage.model.Notlage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class NotlagenInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private NotlageRepository notlagen;


    /**
     * Erstellt Notlagen und persistiert sie mit  {@link NotlageRepository}.
     *
     * @param Notlagen must not be {@literal null}.
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (notlagen.count() != 0) {
            return;
        }

        DVP dvp1 = new DVP(1l);
        DVP dvp2 = new DVP(2l);

        Nachricht nachricht1 = new Nachricht("PAYLOAD1");
        Nachricht nachricht2 = new Nachricht("PAYLOAD2");

        Notlage n1 = new Notlage(dvp1, nachricht1);
        Notlage n2 = new Notlage(dvp2, nachricht2);

        notlagen.saveAll(Arrays.asList(n1, n2));
    }
}
