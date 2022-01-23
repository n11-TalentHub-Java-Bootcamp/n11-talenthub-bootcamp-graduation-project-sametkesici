import React, { useState } from 'react';

export default function HomePage() {


    return (
        <div className='container' style={{ height: '70vh', width: '', display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
            <form className="row mt-5" style={{ width: '500px' }}>
                <div className='col-md-12'>
                    <div className="col-md-12">
                        <label htmlFor="validationDefault01" className="form-label">İsim</label>
                        <input type="text" className="form-control" id="validationDefault01" defaultValue="Mark" required />
                    </div>
                    <div className="col-md-12 mt-3">
                        <label htmlFor="validationDefault02" className="form-label">Soyisim</label>
                        <input type="text" className="form-control" id="validationDefault02" defaultValue="Otto" required />
                    </div>
                    <div className="col-md-12 mt-3">
                        <label htmlFor="validationDefaultUsername" className="form-label">Kimlik Numarası</label>
                        <input type="text" className="form-control" id="validationDefaultUsername" aria-describedby="inputGroupPrepend2" required />
                    </div>
                    <div className="col-md-12 mt-3">
                        <label htmlFor="validationDefault03" className="form-label">Telefon Numarası</label>
                        <input type="text" className="form-control" id="validationDefault03" required />
                    </div>
                    <div className="col-md-12 mt-3">
                        <label htmlFor="validationDefault05" className="form-label">Aylık Maaş</label>
                        <input type="text" className="form-control" id="validationDefault05" required />
                    </div>
                    <div className="col-md-12 mt-3">
                        <label htmlFor="validationDefault05" className="form-label">Teminat(Opsiyonel)</label>
                        <input type="text" className="form-control" id="validationDefault05" />
                    </div>
                    <div className="col-12 mt-3">
                        <button className="btn btn-primary" type="submit">Submit form</button>
                    </div>
                </div>
            </form>
        </div>
    )

}
