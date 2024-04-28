package EvoMon.DataIntegration.Service;


import EvoMon.DataIntegration.Model.MedImage;
import EvoMon.DataIntegration.Model.MedImageSerie;
import EvoMon.DataIntegration.Repository.MedImageRepository;
import EvoMon.DataIntegration.Repository.MedImageSerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
//@Transactional
public class MedImagesAndSeriesService {

    @Autowired
    private MedImageSerieRepository medImageSerieRepository;

    @Autowired
    private MedImageRepository medImageRepository;

    private final int BATCH_SIZE = 1000;

    public void generateMedImagesAndSeries() {
        List<MedImageSerie> medImageSeries = generateMedImageSeries(1_000_000);
        List<MedImage> medImages = generateMedImages(medImageSeries, 25);
        ExecutorService medImageSeriesExecutor = Executors.newFixedThreadPool(8);

        for (int i = 0; i < medImageSeries.size(); i += BATCH_SIZE) {
            List<MedImageSerie> batch = medImageSeries.subList(i, Math.min(i + BATCH_SIZE, medImageSeries.size()));
            medImageSeriesExecutor.execute(() -> medImageSerieRepository.saveAll(batch));
        }
        medImageSeriesExecutor.shutdown();

        ExecutorService medImagesExecutor = Executors.newFixedThreadPool(8);
        for (int i = 0; i < medImages.size(); i += BATCH_SIZE) {
            List<MedImage> batch2 = medImages.subList(i, Math.min(i + BATCH_SIZE, medImages.size()));
            medImagesExecutor.execute(() ->{
                if (!batch2.isEmpty()){
                    medImageRepository.saveAll(batch2);
                }else {
                    System.out.println("kati stravwse");
                }
            });
        }
        medImagesExecutor.shutdown();
    }

    private List<MedImageSerie> generateMedImageSeries(int count) {
        List<MedImageSerie> medImageSeries = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            MedImageSerie medImageSerie = new MedImageSerie();
            medImageSerie.setDescription("Test");
            medImageSeries.add(medImageSerie);
        }
        return medImageSeries;
    }

    private List<MedImage> generateMedImages(List<MedImageSerie> medImageSeries, int medImagesPerSerie) {
        List<MedImage> medImages = new ArrayList<>();
        for (MedImageSerie medImageSerie : medImageSeries) {
            for (int i = 0; i < medImagesPerSerie; i++) {
                MedImage medImage = new MedImage();
                medImage.setMedImageSerie(medImageSerie);
                medImages.add(medImage);
            }
        }
        return medImages;
    }
}

