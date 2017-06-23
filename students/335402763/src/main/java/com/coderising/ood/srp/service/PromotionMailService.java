package com.coderising.ood.srp.service;

import java.io.File;

public interface PromotionMailService {

	void sendPromotionMail(File file, boolean mailDebug) throws Exception;

}
